package com.wecanteven.AreaView.ViewObjects.Factories;

import com.sun.glass.ui.View;
import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.HUDDecorator;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.BipedMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.BuffRingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Stats.Stat;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import javafx.geometry.Pos;

/**
 * Created by adamfortier on 4/17/16.
 */
public class HominidVOFactory {
    private SimpleVOFactory simpleVOFactory;
    private EquipableItemVOFactory equipableItemVOFactory;
    private JumpDetector jumpDetector;
    private HandStateFactory handStateFactory;


    public HominidVOFactory(SimpleVOFactory simpleVOFactory, EquipableItemVOFactory equipableItemVOFactory, JumpDetector jumpDetector) {
        this.simpleVOFactory = simpleVOFactory;
        this.equipableItemVOFactory = equipableItemVOFactory;
        this.jumpDetector = jumpDetector;
        this.handStateFactory = new HandStateFactory(this);
    }

    public MicroPositionableViewObject createWeaponHand(Position p, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        SimpleViewObject hand = simpleVOFactory.createSimpleViewObject(p, "Hands/" + color + "/hand.xml");
        EquipableViewObject equipable = simpleVOFactory.createEquipable(hand, slot, equipableItemVOFactory, weaponSubject , color);
        return simpleVOFactory.createMicroPositionableViewObject(equipable);
    }

    @Deprecated
    public MicroPositionableViewObject createHandWithWeapon(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(equipableItemVOFactory.createEquipable(equipableItemVOFactory.createEquipment(position, entity, weaponName, color), slot, entity, color));
    }

    public MicroPositionableViewObject createHand(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(equipableItemVOFactory.createEquipable(simpleVOFactory.createSimpleViewObject(position, "Hands/" + color + "/hand.xml"), slot, entity, color));

    }

    public MicroPositionableViewObject createFoot(Position position, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createSimpleViewObject(position, "Feet/" + color + "/Foot.xml"));
    }

    public HominidViewObject createHominid(Position position, Character entity, ViewObject body, ViewObject head, HandsViewObject hands, FeetViewObject feet, BuffRingViewObject buffs){
        return simpleVOFactory.createHominid(position, entity, body, head, hands, feet, buffs, jumpDetector);
    }


    public HUDDecorator createHominidHUDDecorator(HominidViewObject hominidViewObject, Stats stats) {
        return simpleVOFactory.createHUDDecorator(hominidViewObject, stats);
    }

    public <T extends Moveable & ViewObservable> BipedMovingViewObject createBipedMovingObjectWithCharacterSubject(T subject, ViewObject child) {
        return simpleVOFactory.createBipedMovingViewObject(subject, child, jumpDetector);
    }

    public HandsViewObject createHandsViewObject(Position p, EquipmentSlot slot, Entity entity, GameColor color) {
        return new HandsViewObject(p, slot, handStateFactory, entity, color);
    }

    private <T extends Directional & ViewObservable> DirectionalViewObject createBody(Position p, T d, String entityName) {
        return simpleVOFactory.createDirectional(p, d, "Entities/" +  entityName + "/");
    }





}
