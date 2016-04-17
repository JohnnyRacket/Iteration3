package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.BackgroundDrawable;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.*;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.BipedMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.SimpleMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.BuffRingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.*;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetFlyingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetJumpingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetWalkingStrategy;
import com.wecanteven.AreaView.ViewObjects.Parallel.DarkenedViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.*;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Abilities.MovableHitBox;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class ViewObjectFactory {
    private HexDrawingStrategy hexDrawingStrategy =  new HexDrawingStrategy();
    private AreaView areaView;
    private DynamicImageFactory factory = DynamicImageFactory.getInstance();
    private JumpDetector jumpDetector;
    private SimpleVOFactory simpleVOFactory;
    private EquipableItemVOFactory equipableItemVOFactory;



    public ViewObjectFactory(AreaView areaView, Map gameMap) {
        //this.hexDrawingStrategy = new HexDrawingStrategy();
        this.areaView = areaView;
        this.jumpDetector = new JumpDetector(gameMap);
        this.simpleVOFactory = new SimpleVOFactory(hexDrawingStrategy, areaView);
        this.hexDrawingStrategy.setCenterTarget(
                simpleVOFactory.createSimpleViewObject(
                        new Position(0,0,0),
                        "null.xml"
                )
        );
        this.equipableItemVOFactory = new EquipableItemVOFactory(simpleVOFactory);
    }

    public SimpleVOFactory getSimpleVOFactory() {
        return simpleVOFactory;
    }

    public ViewObject createGround(Position p) {
        return simpleVOFactory.createSimpleViewObject(p, "Terrain/Grass.xml");
    }

    public ViewObject createWater(Position p) {
        return simpleVOFactory.createSimpleViewObject(p, "Terrain/Water.xml");
    }

    public ViewObject createCurrent(Position p) {
        return simpleVOFactory.createSimpleViewObject(p, "Terrain/Current.xml");
    }


    //This method is OG
    public ViewObject createBaseHominoid(Position p, Character character, String face) {
        GameColor color = character.getColor();
        EquipmentSlot chestSlot = character.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = character.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = character.getItemStorage().getEquipped().getHead();

        //Create the body and decorate it with body armor
        SimpleViewObject body = new SimpleViewObject(p,
                factory.loadDynamicImage("Entities/Beans/" + color + ".xml"),
                hexDrawingStrategy);
        EquipableViewObject bodyArmor = equipableItemVOFactory.createEquipable(body, chestSlot, character, color);

        //Create the face and decorate it with a hat
        DirectionalViewObject head = simpleVOFactory.createDirectional(p, character, "Face/" + face + "/");
        EquipableViewObject hatArmor = equipableItemVOFactory.createEquipable(
                head,
                hatSlot,
                character,
                color);

        //Create a pair of hands
        MicroPositionableViewObject leftHand = createHand(p, weaponSlot, character, color);
        MicroPositionableViewObject rightHand = createHand(p, weaponSlot, character, color);
        HandsViewObject hands = new HandsViewObject(leftHand, rightHand,
                Direction.SOUTH, p,
                weaponSlot, this,
                character,
                (d, left, right) -> new BrawlingState(d, left, right),
                color);

        //Create some feet
        MicroPositionableViewObject leftFoot = createMicroPositionableViewObject(p,
                        "Feet/" + color + "/Foot.xml");
        MicroPositionableViewObject rightFoot = createMicroPositionableViewObject(p,
                "Feet/" + color + "/Foot.xml");
        FeetViewObject feet = new FeetViewObject(Direction.SOUTH, leftFoot, rightFoot,
                new FeetWalkingStrategy(0.1, leftFoot, rightFoot),
                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot),
                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot));

        //Create the buff thingy
        BuffRingViewObject buffs = new BuffRingViewObject(p, this, character.getBuffmanager());

        //Finnally create the Hominoid
        HominidViewObject hominoid = new  HominidViewObject(
                p,
                character,
                bodyArmor,
                hatArmor,
                hands,
                feet,
                buffs,
                jumpDetector);

        //And give him a HUD
        HUDDecorator homioidWithHUD = new HUDDecorator(
                hominoid,
                character.getStats(),
                hexDrawingStrategy,
                simpleVOFactory,
                areaView);




        //Make a moving view object
        BipedMovingViewObject moivingHominoidWithHUD = createBipedMovingViewObject(character, homioidWithHUD);


        //Now give him a death animation
        StartableViewObject startableViewObject = new StartableViewObject(p, factory.loadActiveDynamicImage("Death/Light Blue.xml"), hexDrawingStrategy);

        //And return the new destroyable VO
        DestroyableViewObject destroyableViewObject = new DestroyableViewObject(
                moivingHominoidWithHUD,
                startableViewObject,
                character,
                areaView,
                800);

        //Finally return a moving avatar
        return hominoid;
    }

    public ViewObject createBird(Position p, Character character, String face) {
        GameColor color = character.getColor();

        EquipmentSlot chestSlot = character.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = character.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = character.getItemStorage().getEquipped().getHead();

        //Create the body and decorate it with body armor
        SimpleViewObject body = new SimpleViewObject(p,
                factory.loadDynamicImage("Entities/MiniBeans/" + color.light + ".xml"),
                hexDrawingStrategy);
        EquipableViewObject bodyArmor = equipableItemVOFactory.createEquipable(body, chestSlot, character, character.getColor());

        //Create the face and decorate it with a hat
        DirectionalViewObject head = simpleVOFactory.createDirectional(p, character, "Face/" + face + "/");
        EquipableViewObject hatArmor = equipableItemVOFactory.createEquipable(
                head,
                hatSlot,
                character,
                color);

        //Create a pair of hands
        MicroPositionableViewObject leftHand = createWing(p, weaponSlot, character, color);
        MicroPositionableViewObject rightHand = createWing(p, weaponSlot, character, color);
        HandsViewObject hands = new HandsViewObject(leftHand, rightHand,
                Direction.SOUTH, p,
                weaponSlot, this,
                character,
                (d, left, right) -> new WingState(d, left, right),
                color);

        //Create some feet
        MicroPositionableViewObject leftFoot = createMicroPositionableViewObject(p,
                "Feet/" + "Bird" + "/Foot.xml");
        MicroPositionableViewObject rightFoot = createMicroPositionableViewObject(p,
                "Feet/" + "Bird" + "/Foot.xml");
        FeetViewObject feet = new FeetViewObject(Direction.SOUTH, leftFoot, rightFoot,
                new FeetJumpingStrategy(0, 2,  leftFoot, rightFoot),
                new FeetFlyingStrategy(1, leftFoot, rightFoot),
                new FeetFlyingStrategy(1, leftFoot, rightFoot));

        //Create the buff thingy
        BuffRingViewObject buffs = new BuffRingViewObject(p, this, character.getBuffmanager());

        //Finnally create the Hominoid
        HominidViewObject hominoid = new  HominidViewObject(
                p,
                character,
                bodyArmor,
                hatArmor,
                hands,
                feet,
                buffs,
                jumpDetector);

        //And give him a HUD
        HUDDecorator homioidWithHUD = new HUDDecorator(
                hominoid,
                character.getStats(),
                hexDrawingStrategy,
                simpleVOFactory,
                areaView);




        //Make a moving view object
        BipedMovingViewObject moivingHominoidWithHUD = createBipedMovingViewObject(character, homioidWithHUD);


        //Now give him a death animation
        StartableViewObject startableViewObject = new StartableViewObject(p, factory.loadActiveDynamicImage("Death/Light Blue.xml"), hexDrawingStrategy);

        //And return the new destroyable VO
        DestroyableViewObject destroyableViewObject = new DestroyableViewObject(
                moivingHominoidWithHUD,
                startableViewObject,
                character,
                areaView,
                800);

        //Finally return a moving avatar
        return hominoid;
    }

    public DestroyableViewObject createTakeableItem(Position position, TakeableItem takeableItem) {
        String name = takeableItem.getName();
        DestroyableViewObject destroyableViewObject =  new DestroyableViewObject(
                new SimpleViewObject(position,
                        factory.loadDynamicImage("Items/" + name + "/" + name + ".xml"),
                        hexDrawingStrategy),
                new StartableViewObject(position,
                        factory.loadActiveDynamicImage("Items/" + name + "/" + name + ".xml"),
                        hexDrawingStrategy),
                takeableItem,
                areaView,
                1);
        return destroyableViewObject;
    }

    public BackgroundDrawable createBackgroundDrawable(ViewObject centerTarget) {
        return new BackgroundDrawable(factory.loadDynamicImage("Textures/DarkBlue.xml"), getDrawingStrategy(), centerTarget);
    }

    public ViewObject createRangedEffect(MovableHitBox m) {
        ViewObject vo = simpleVOFactory.createDirectional(m.getLocation().toPosition(), m, "Effects/WaterBolt/");
        SimpleMovingViewObject viewObject = createSimpleMovingViewObject(m, vo);
        DestroyableViewObject destroyableMovingDirectionVO = new DestroyableViewObject(viewObject, simpleVOFactory.createStartableViewObject(m.getLocation().toPosition(), "null.xml"), m, areaView, 100);
        return destroyableMovingDirectionVO;
    }

    public DestroyableViewObject createOneShotItem(Position position, OneShot oneShot) {
        StartableDynamicImage animation = factory.loadActiveDynamicImage("Items/" + oneShot.getName() + "/" + oneShot.getName() + ".xml");

        StartableViewObject internalVO = new StartableViewObject(position, animation, hexDrawingStrategy);
        DestroyableViewObject destroyableVO = new DestroyableViewObject(internalVO, internalVO, oneShot, areaView, 1000);
        return destroyableVO;
    }

    private MicroPositionableViewObject createHand(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Hands/" + color.name + "/hand.xml"), hexDrawingStrategy), slot, entity, color));
    }

    private MicroPositionableViewObject createWing(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Wings/" + color.name + "/hand.xml"), hexDrawingStrategy), slot, entity, color));
    }

    public SimpleViewObject createObstacle(Position position, Obstacle obstacle) {
        return new SimpleViewObject(
                position,
                factory.loadDynamicImage("Items/" + obstacle.getName() + "/" + obstacle.getName() + ".xml"),
                hexDrawingStrategy);
    }

    public DecalViewObject createDecalViewObject(Position position, Decal decal) {
        return new DecalViewObject(
                simpleVOFactory.createSimpleViewObject(position, "Decals/" + decal.getName() + ".xml"),
                decal.getR(),
                decal.getS()
        );
    }

    public MicroPositionableViewObject createBuff(Position p, String name) {
        return createMicroPositionableViewObject(p, "Buffs/" + name + ".xml");
    }

    @Deprecated
    private MicroPositionableViewObject createLeftFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject leftFootDirectional = simpleVOFactory.createDirectional(position, entity, "Feet/Brown/Left/");
        entity.attach(leftFootDirectional);
        return new MicroPositionableViewObject(leftFootDirectional);
    }

    @Deprecated
    private MicroPositionableViewObject createRightFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject rightFootDirectional = simpleVOFactory.createDirectional(position, entity, "Feet/Brown/Right/");
        entity.attach(rightFootDirectional);
        return new MicroPositionableViewObject(rightFootDirectional);
    }

    private MicroPositionableViewObject createSimpleRightHand(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Hands/" + color + "/hand.xml"), hexDrawingStrategy), slot, entity, color));
    }

    public MicroPositionableViewObject createSimpleLeftHand(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(new SimpleViewObject(position, factory.loadDynamicImage("Hands/" + color + "/hand.xml"), hexDrawingStrategy), slot, entity, color));
    }

    public ViewObject createInteractableItem(Position p, InteractiveItem interactiveItem) {
        ActivatableViewObject vo = new ActivatableViewObject(p,
                interactiveItem,
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Active.xml"),
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Inactive.xml"),
                hexDrawingStrategy);
        interactiveItem.attach(vo);
        return vo;
    }

    public ViewObject createEquipment(Position p, Entity entity, String name, GameColor color) {
        //First we try to find a nondirectional equipment
        try {
            return simpleVOFactory.createSimpleViewObject(p, "Equipment/" + color + "/" + name + ".xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        DirectionalViewObject directionalViewObject =  simpleVOFactory.createDirectional(p, entity, "Equipment/" +color.name + "/" + name + "/");
        entity.attach(directionalViewObject);
        return directionalViewObject;
    }

    private <T extends  Directional & ViewObservable> DirectionalViewObject createBody(Position p, T d, String entityName) {
        return simpleVOFactory.createDirectional(p, d, "Entities/" +  entityName + "/");
    }

    public ParallelViewObject createFogOfWarViewObject(Position p) {
        return new DarkenedViewObject(p);
    }


    public <T extends Moveable & ViewObservable> BipedMovingViewObject createBipedMovingViewObject(T subject, ViewObject child) {
        BipedMovingViewObject mvo = new BipedMovingViewObject(child, subject, areaView, jumpDetector);
        return mvo;
    }

    public <T extends Moveable & ViewObservable> SimpleMovingViewObject createSimpleMovingViewObject(T subject, ViewObject child) {
        SimpleMovingViewObject mvo = new SimpleMovingViewObject(child, subject, areaView);
        return mvo;
    }


    /*
    *
    *   Hand States
    *
     */

    public HandState createOneHandedWeaponState(Position position, Direction direction, EquipmentSlot slot, String weaponName, Entity entity, GameColor color) {
        return new OneHandedWeaponState(direction, createRightHandWeaponObject(position, direction, weaponName, slot, entity, color), createHand(position, null, entity, color), this, entity);
    }

    public HandState createDualWieldMeleeWeaponState(Position position, Direction direction, EquipmentSlot slot, String weaponName, Entity entity, GameColor color) {
        return new DualWieldState(direction, createLeftHandWeapon(position, direction, weaponName, slot, entity, color), createRightHandWeaponObject(position, direction, weaponName, slot, entity, color), entity);
    }


    public MicroPositionableViewObject createLeftHandWeapon(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(createSimpleLeftHand(position, slot, entity, color), slot, entity, color));
    }

    public MicroPositionableViewObject createRightHandWeaponObject(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(createSimpleRightHand(position, slot, entity, color), slot, entity, color));
    }


    public MicroPositionableViewObject createMicroPositionableViewObject(Position position, String path) {
        SimpleViewObject simpleViewObject = simpleVOFactory.createSimpleViewObject(position, path);
        return new MicroPositionableViewObject(simpleViewObject);
    }


    public HexDrawingStrategy getDrawingStrategy() {
        return hexDrawingStrategy;
    }

    public DynamicImageFactory getDynamicImageFactory() {
        return factory;
    }


    public SimpleViewObject createAoe(Position position, String name) {
        return simpleVOFactory.createSimpleViewObject(position, "AreaOfEffects/" + name + ".xml");
    }

    public ViewObject createHitBox(HitBox hitBox) {
        String path = "Effects/" + hitBox.getName() + "/" + hitBox.getName() + ".xml";
        Position p = hitBox.getLocation().toPosition();
        StartableViewObject hitBoxVO = simpleVOFactory.createStartableViewObject(p, path);
        hitBoxVO.start(hitBox.getDuration());
        return hitBoxVO;
    }


}
