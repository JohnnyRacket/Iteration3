package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.BackgroundDrawable;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.*;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.BipedMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.MovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.SimpleMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.BuffRingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.*;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetFlyingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetJumpingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetWalkingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.MountViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.DarkenedViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.*;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Abilities.MovableHitBox;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.TakeableMoveable;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
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
    private SimpleVOFactory simpleVOFactory;
    private HominidVOFactory hominidVOFactory;
    private EquipableItemVOFactory equipableItemVOFactory;
    private HandStateFactory handStateFactory;



    public ViewObjectFactory(AreaView areaView, Map gameMap) {
        //this.hexDrawingStrategy = new HexDrawingStrategy();
        this.areaView = areaView;
        this.simpleVOFactory = new SimpleVOFactory(hexDrawingStrategy, areaView, gameMap);
        this.hexDrawingStrategy.setCenterTarget(
                simpleVOFactory.createSimpleViewObject(
                        new Position(0,0,0),
                        "null.xml"
                )
        );
        this.equipableItemVOFactory = simpleVOFactory.getEquipableItemVOFactory();
        this.hominidVOFactory = simpleVOFactory.getHominidVOFactory();
        this.handStateFactory = simpleVOFactory.getHandStateFactory();
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


    public ViewObject createMount(Position p, Mount mount) {
        GameColor color = mount.getColor();
//        EquipmentSlot chestSlot = character.getItemStorage().getEquipped().getChest();
//        EquipmentSlot weaponSlot = character.getItemStorage().getEquipped().getWeapon();
//        EquipmentSlot hatSlot = character.getItemStorage().getEquipped().getHead();

        //Create the body and decorate it with body armor
        //SimpleViewObject body = simpleVOFactory.createSimpleViewObject(p, "Entities/Beans/" + color + ".xml");

        //EquipableViewObject bodyArmor = equipableItemVOFactory.createEquipable(body, chestSlot, character, color);

        //Create the face and decorate it with a hat
        //DirectionalViewObject head = simpleVOFactory.createDirectional(p, character, "Face/" + face + "/");
        //EquipableViewObject hatArmor = equipableItemVOFactory.createEquipable(head, hatSlot, character, color);

        //Create a pair of hands
//        MicroPositionableViewObject leftHand = hominidVOFactory.createHand(p, weaponSlot, character, color);
//        MicroPositionableViewObject rightHand = hominidVOFactory.createHand(p, weaponSlot, character, color);
//        HandsViewObject hands = hominidVOFactory.createHandsViewObject(p, weaponSlot, character, color);

        //Create some feet
//        MicroPositionableViewObject leftFoot = hominidVOFactory.createFoot(p, color);
//        MicroPositionableViewObject rightFoot = hominidVOFactory.createFoot(p, color);
//        FeetViewObject feet = new FeetViewObject(Direction.SOUTH, leftFoot, rightFoot,
//                new FeetWalkingStrategy(0.1, leftFoot, rightFoot),
//                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot),
//                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot));
//
//        //Create the buff thingy
//        BuffRingViewObject buffs = new BuffRingViewObject(p, simpleVOFactory.getEquipableItemVOFactory(), character.getBuffmanager());

        //Finnally create the Hominoid
        MountViewObject mountVO = simpleVOFactory.createMount(p, simpleVOFactory, mount);

        StartableViewObject startableViewObject = simpleVOFactory.createStartableViewObject(p, "Death/Gray.xml");


        DestroyableViewObject destroyableViewObject = simpleVOFactory.createDestroyableViewObject(mountVO, startableViewObject,
                mount, 800);

        BipedMovingViewObject movingMount = hominidVOFactory.createBipedMovingObjectWithCharacterSubject(mount, destroyableViewObject);

        //And give him a HUD
       // HUDDecorator homioidWithHUD = hominidVOFactory.createHominidHUDDecorator(hominoid, character.getStats());

        //Now give him a death animation
       // StartableViewObject startableViewObject = simpleVOFactory.createStartableViewObject(p, "Death/Light Blue.xml");

        //And return the new destroyable VO
//        DestroyableViewObject destroyableViewObject = simpleVOFactory.createDestroyableViewObject(homioidWithHUD, startableViewObject,
//                character, 800);

        //Make a moving view object
//        BipedMovingViewObject moivingHominoidWithHUD = hominidVOFactory.createBipedMovingObjectWithCharacterSubject(character, destroyableViewObject);
//




        //Finally return a moving avatar
        return movingMount;
    }

    //This method is OG
    public ViewObject createBaseHominoid(Position p, Character character, String face) {
        GameColor color = character.getColor();
        EquipmentSlot chestSlot = character.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = character.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = character.getItemStorage().getEquipped().getHead();

        //Create the body and decorate it with body armor
        SimpleViewObject body = simpleVOFactory.createSimpleViewObject(p, "Entities/Beans/" + color + ".xml");

        EquipableViewObject bodyArmor = equipableItemVOFactory.createEquipable(body, chestSlot, character, color);

        //Create the face and decorate it with a hat
        DirectionalViewObject head = simpleVOFactory.createDirectional(p, character, "Face/" + face + "/");
        EquipableViewObject hatArmor = equipableItemVOFactory.createEquipable(head, hatSlot, character, color);

        //Create a pair of hands
//        MicroPositionableViewObject leftHand = hominidVOFactory.createHand(p, weaponSlot, character, color);
//        MicroPositionableViewObject rightHand = hominidVOFactory.createHand(p, weaponSlot, character, color);
        HandsViewObject hands = hominidVOFactory.createHandsViewObject(p, weaponSlot, character, color);

        //Create some feet
        MicroPositionableViewObject leftFoot = hominidVOFactory.createFoot(p, color);
        MicroPositionableViewObject rightFoot = hominidVOFactory.createFoot(p, color);
        FeetViewObject feet = new FeetViewObject(Direction.SOUTH, leftFoot, rightFoot,
                new FeetWalkingStrategy(0.1, leftFoot, rightFoot),
                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot),
                new FeetJumpingStrategy(0, 2, leftFoot, rightFoot));

        //Create the buff thingy
        BuffRingViewObject buffs = new BuffRingViewObject(p, simpleVOFactory.getEquipableItemVOFactory(), character.getBuffmanager());

        //Finnally create the Hominoid
        HominidViewObject hominoid = hominidVOFactory.createHominid(
                p,
                character,
                bodyArmor,
                hatArmor,
                hands,
                feet,
                buffs);

        //And give him a HUD
        HUDDecorator homioidWithHUD = hominidVOFactory.createHominidHUDDecorator(hominoid, character.getStats());

        //Now give him a death animation
        StartableViewObject startableViewObject = simpleVOFactory.createStartableViewObject(p, "Death/Gray.xml");

        //And return the new destroyable VO
        DestroyableViewObject destroyableViewObject = simpleVOFactory.createDestroyableViewObject(homioidWithHUD, startableViewObject,
                character, 800);

        //Make a moving view object
        BipedMovingViewObject moivingHominoidWithHUD = hominidVOFactory.createBipedMovingObjectWithCharacterSubject(character, destroyableViewObject);





        //Finally return a moving avatar
        return moivingHominoidWithHUD;
    }

    public ViewObject createBird(Position p, Character character, String face) {
        GameColor color = character.getColor();

        EquipmentSlot chestSlot = character.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = character.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = character.getItemStorage().getEquipped().getHead();

        //Create the body and decorate it with body armor
        SimpleViewObject body = simpleVOFactory.createSimpleViewObject(p, "Entities/MiniBeans/" + GameColor.BLUE + ".xml");

//                new SimpleViewObject(p,
//                factory.loadDynamicImage("Entities/MiniBeans/" + GameColor.BLUE + ".xml"),
//                hexDrawingStrategy);
        EquipableViewObject bodyArmor = equipableItemVOFactory.createEquipable(body, chestSlot, character, character.getColor());

        //Create the face and decorate it with a hat
        DirectionalViewObject head = simpleVOFactory.createDirectional(p, character, face + "/");
//        EquipableViewObject hatArmor = equipableItemVOFactory.createEquipable(
//                head,
//                hatSlot,
//                character,
//                color);

        //Create a pair of hands
        MicroPositionableViewObject leftHand = hominidVOFactory.createWing(p, weaponSlot, character, GameColor.BLUE);
        MicroPositionableViewObject rightHand = hominidVOFactory.createWing(p, weaponSlot, character, GameColor.BLUE);
        WingsViewObject hands = new WingsViewObject(p, weaponSlot, handStateFactory, character, color);

        //Create some feet
        MicroPositionableViewObject leftFoot = simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createSimpleViewObject(p,
                "Feet/" + "Bird" + "/Foot.xml"));
        MicroPositionableViewObject rightFoot = simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createSimpleViewObject(p,
                "Feet/" + "Bird" + "/Foot.xml"));
        FeetViewObject feet = new FeetViewObject(Direction.SOUTH, leftFoot, rightFoot,
                new FeetJumpingStrategy(0, 2,  leftFoot, rightFoot),
                new FeetFlyingStrategy(1, leftFoot, rightFoot),
                new FeetFlyingStrategy(1, leftFoot, rightFoot));

        //Create the buff thingy
        BuffRingViewObject buffs = new BuffRingViewObject(p, equipableItemVOFactory, character.getBuffmanager());

        //Finnally create the Hominoid
        HominidViewObject hominoid = hominidVOFactory.createHominid(
                p,
                character,
                bodyArmor,
                head,
                hands,
                feet,
                buffs);

        //And give him a HUD
        HUDDecorator homioidWithHUD = new HUDDecorator(
                hominoid,
                character.getStats(),
                hexDrawingStrategy,
                simpleVOFactory,
                areaView);




        //Make a moving view object

        //Now give him a death animation
        StartableViewObject startableViewObject = new StartableViewObject(p, factory.loadActiveDynamicImage("Death/Gray.xml"), hexDrawingStrategy);

        //And return the new destroyable VO
        DestroyableViewObject destroyableViewObject = new DestroyableViewObject(
                homioidWithHUD,
                startableViewObject,
                character,
                areaView,
                800);

        BipedMovingViewObject moivingHominoidWithHUD = hominidVOFactory.createBipedMovingObjectWithCharacterSubject(character, destroyableViewObject);

        //Finally return a moving avatar
        return moivingHominoidWithHUD;
    }

    public ViewObject createTakeableItem(Position position, TakeableMoveable movingSubject, TakeableItem takeableItem) {
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
        return simpleVOFactory.createSimpleMovingViewObject(movingSubject, destroyableViewObject);
    }

    public BackgroundDrawable createBackgroundDrawable(ViewObject centerTarget) {
        return new BackgroundDrawable(factory.loadDynamicImage("Textures/DarkBlue.xml"), getDrawingStrategy(), centerTarget);
    }

    public ViewObject createRangedEffect(MovableHitBox m) {
        ViewObject vo = simpleVOFactory.createDirectional(m.getLocation().toPosition(), m, "Effects/WaterBolt/");
        SimpleMovingViewObject viewObject = simpleVOFactory.createSimpleMovingViewObject(m, vo);
        DestroyableViewObject destroyableMovingDirectionVO = new DestroyableViewObject(viewObject, simpleVOFactory.createStartableViewObject(m.getLocation().toPosition(), "null.xml"), m, areaView, 100);
        return destroyableMovingDirectionVO;
    }





    public ParallelViewObject createFogOfWarViewObject(Position p) {
        return new DarkenedViewObject(p);
    }



    /*
    *
    *   Hand States
    *
     */

    public HandState createDualWieldMeleeWeaponState(Position position, Direction direction, EquipmentSlot slot, String weaponName, Entity entity, GameColor color) {
        return new BrawlingState(direction, createLeftHandWeapon(position, direction, weaponName, slot, entity, color), createRightHandWeaponObject(position, direction, weaponName, slot, entity, color));
    }

    public MicroPositionableViewObject createLeftHandWeapon(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return hominidVOFactory.createHandWithWeapon(position, direction, weaponName, slot, entity, color);  //new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(createSimpleRightHand(position, slot, entity, color), slot, entity, color));
    }

    public MicroPositionableViewObject createRightHandWeaponObject(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return hominidVOFactory.createHandWithWeapon(position, direction, weaponName, slot, entity, color);  //new MicroPositionableViewObject(equipableItemVOFactory.createEquipable(createSimpleRightHand(position, slot, entity, color), slot, entity, color));
    }



    public HexDrawingStrategy getDrawingStrategy() {
        return hexDrawingStrategy;
    }

    public DynamicImageFactory getDynamicImageFactory() {
        return factory;
    }


}
