package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.BackgroundDrawable;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.*;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandState;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.OneHandedWeaponState;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.*;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 3/31/2016.
 */
public abstract class ViewObjectFactory {
    private HexDrawingStrategy hexDrawingStrategy;
    private AreaView areaView;
    private DynamicImageFactory factory = DynamicImageFactory.getInstance();
    private JumpDetector jumpDetector;


    public ViewObjectFactory(AreaView areaView, Map gameMap) {
        this.hexDrawingStrategy = new HexDrawingStrategy();
        this.hexDrawingStrategy.setCenterTarget(
                createSimpleViewObject(
                        new Position(0,0,0),
                        "null.xml"
                )
        );
        this.areaView = areaView;
        this.jumpDetector = new JumpDetector(gameMap);
    }

    public abstract ViewObject createGround(Position p);
    public abstract ViewObject createWater(Position p);



    public ViewObject createSneak(Position p, Direction d, Character subject) {
        EquipmentSlot chestSlot = subject.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = subject.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = subject.getItemStorage().getEquipped().getHead();


        DirectionalViewObject body = createBody(p, subject, "Sneak");
        EquipableViewObject bodyArmor = createEquipable(body, createNullViewObject(), chestSlot, subject);
        EquipableViewObject hatArmor = createEquipable(bodyArmor, createEquipment(p, subject, "Shaved"), hatSlot, subject);

        chestSlot.attach(bodyArmor);
        hatSlot.attach(hatArmor);


        MicroPositionableViewObject leftHand = new MicroPositionableViewObject(createSimpleLeftHand(p, weaponSlot, subject));
        MicroPositionableViewObject rightHand = new MicroPositionableViewObject(createSimpleRightHand(p, weaponSlot, subject));
        HandsViewObject hands = new HandsViewObject(leftHand, rightHand, d, p, weaponSlot, this, subject);

        weaponSlot.attach(hands);

        MicroPositionableViewObject leftFoot = createLeftFoot(p, d, subject);
        MicroPositionableViewObject rightFoot = createRightFoot(p, d, subject);

        FeetViewObject feet = new FeetViewObject(d, leftFoot, rightFoot);
        HominidViewObject stationarySneak = new  HominidViewObject(p, d, subject, subject, hatArmor, hands, feet, jumpDetector);
        HUDDecorator sneakWithHUD = new HUDDecorator(stationarySneak, subject.getStats(), hexDrawingStrategy);
        subject.attach(stationarySneak);
        subject.attach(body);
        subject.getStats().attach(sneakWithHUD);

        VisibilitySourceViewObject visibilitySourceViewObject = new VisibilitySourceViewObject(sneakWithHUD, subject, areaView, 3);
        subject.attach(visibilitySourceViewObject);
        //TEMPORARY TESTING WORKAROUND
        //TODO: make better
        hexDrawingStrategy.setCenterTarget(stationarySneak);


        return createMovingViewObject(subject, visibilitySourceViewObject);

    }

    public BackgroundDrawable createBackgroundDrawable(ViewObject centerTarget) {
        return new BackgroundDrawable(factory.loadDynamicImage("Textures/DarkBlue.xml"), getDrawingStrategy(), centerTarget);
    }

    public DestroyableViewObject createOneShotItem(Position position, OneShot oneShot) {
        StartableDynamicImage animation = factory.loadActiveDynamicImage("Items/" + oneShot.getName() + "/" + oneShot.getName() + ".xml");

        StartableViewObject internalVO = new StartableViewObject(position, animation, hexDrawingStrategy);
        DestroyableViewObject destroyableVO = new DestroyableViewObject(internalVO, oneShot);
        oneShot.attach(destroyableVO);
        return destroyableVO;
    }

    public SimpleViewObject createObstacle(Position position, Obstacle obstacle) {
        return new SimpleViewObject(
                position,
                factory.loadDynamicImage("Items/" + obstacle.getName() + "/" + obstacle.getName() + ".xml"),
                hexDrawingStrategy);
    }

    private MicroPositionableViewObject createLeftFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject leftFootDirectional = createDirectional(position, entity, "Feet/Brown/Left/");
        entity.attach(leftFootDirectional);
        return new MicroPositionableViewObject(leftFootDirectional);
    }

    private MicroPositionableViewObject createRightFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject rightFootDirectional = createDirectional(position, entity, "Feet/Brown/Right/");
        entity.attach(rightFootDirectional);
        return new MicroPositionableViewObject(rightFootDirectional);
    }

    private ViewObject createSimpleRightHand(Position position, EquipmentSlot slot, Entity entity) {
        return createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Hands/Human/hand.xml"), hexDrawingStrategy), null, slot, entity);
    }

    private ViewObject createSimpleLeftHand(Position position, EquipmentSlot slot, Entity entity) {
        return createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Hands/Human/hand.xml"), hexDrawingStrategy), null, slot, entity);
    }
//    private FeetViewObject createFeet(Position p, Direction d, String name) {
//        FootViewObject leftFoot = createFoot(p, d, name + "/Left");
//        FootViewObject rightFoot = createFoot(p, d, name + "/Right");
//        return new FeetViewObject(leftFoot, rightFoot, p, d);
//    }

    public ViewObject createInteractableItem(Position p, InteractiveItem interactiveItem) {
        ActivatableViewObject vo = new ActivatableViewObject(p,
                interactiveItem,
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Active.xml"),
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Inactive.xml"),
                hexDrawingStrategy);
        interactiveItem.attach(vo);
        return vo;
    }

    public DirectionalViewObject createEquipment(Position p, Entity entity, String name ) {
        DirectionalViewObject directionalViewObject =  createDirectional(p, entity, "Equipment/" + name + "/");
        entity.attach(directionalViewObject);
        return directionalViewObject;
    }

    private DirectionalViewObject createBody(Position p, Directional d, String entityName) {
        return createDirectional(p, d, "Entities/" +  entityName + "/");
    }

    public FogOfWarViewObject createFogOfWarViewObject(Position p) {
        return new FogOfWarViewObject(p);
    }

    private DirectionalViewObject createDirectional(Position p, Directional d, String path) {
        SimpleDynamicImage bodyNorth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "north.xml");
        SimpleDynamicImage bodySouth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "south.xml");
        SimpleDynamicImage bodyNorthEast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northeast.xml");
        SimpleDynamicImage bodyNorthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northwest.xml");
        SimpleDynamicImage bodySoutheast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southeast.xml");
        SimpleDynamicImage bodySouthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southwest.xml");
        return new DirectionalViewObject(p, d, hexDrawingStrategy, bodyNorth, bodySouth, bodyNorthEast, bodyNorthWest, bodySoutheast, bodySouthWest);
    }

    private MovingViewObject createMovingViewObject(Entity subject, ViewObject child) {
        MovingViewObject mvo = new  MovingViewObject(child, subject, areaView, jumpDetector);
        subject.attach(mvo);
        return mvo;
    }

    public HandState createOneHandedWeaponState(Position position, Direction direction, EquipmentSlot slot, String weaponName, Entity entity) {
        return new OneHandedWeaponState(direction, new MicroPositionableViewObject(createSimpleLeftHand(position, slot, entity)) , createRightHandWeaponObject(position, direction, weaponName, slot, entity));
    }


    public MicroPositionableViewObject createRightHandWeaponObject(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity) {
        return new MicroPositionableViewObject(createEquipable(createSimpleRightHand(position, slot, entity), createDirectional(position, entity, "Equipment/" + weaponName + "/" ), slot, entity));
    }

    public MicroPositionableViewObject createMicroPositionableViewObject(Position position, Direction direction, String path, Entity entity) {
        DirectionalViewObject directionalViewObject = createDirectional(position, entity, path);
        entity.attach(directionalViewObject);
        return new MicroPositionableViewObject(directionalViewObject);
    }


    public HexDrawingStrategy getDrawingStrategy() {
        return hexDrawingStrategy;
    }

    public DynamicImageFactory getDynamicImageFactory() {
        return factory;
    }

    public EquipableViewObject createEquipable(ViewObject child, ViewObject equipment, EquipmentSlot subject, Entity entity) {
        return new EquipableViewObject(child, equipment, subject, this, entity);
    }

    public NullViewObject createNullViewObject() {
        return new NullViewObject(new Position(0,0,0));
    }

    protected SimpleViewObject createSimpleViewObject(Position p, String path) {
        return new SimpleViewObject(p, factory.loadDynamicImage(path), hexDrawingStrategy);
    }
}
