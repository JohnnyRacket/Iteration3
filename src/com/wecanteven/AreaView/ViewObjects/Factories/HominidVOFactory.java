package com.wecanteven.AreaView.ViewObjects.Factories;

import com.sun.glass.ui.View;
import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.HUDDecorator;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.BipedMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.BuffRingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
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

/**
 * Created by adamfortier on 4/17/16.
 */
public class HominidVOFactory {
    private SimpleVOFactory simpleVOFactory;
    private EquipableItemVOFactory equipableItemVOFactory;
    private JumpDetector jumpDetector;


    public HominidVOFactory(SimpleVOFactory simpleVOFactory, EquipableItemVOFactory equipableItemVOFactory, JumpDetector jumpDetector) {
        this.simpleVOFactory = simpleVOFactory;
        this.equipableItemVOFactory = equipableItemVOFactory;
        this.jumpDetector = jumpDetector;
    }


    public MicroPositionableViewObject createHandWithWeapon(Position position, Direction direction, String weaponName, EquipmentSlot slot, Entity entity, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createEquipable(createHand(position, slot, entity, color), slot, equipableItemVOFactory, entity, color));
    }

    public MicroPositionableViewObject createHand(Position position, EquipmentSlot slot, Entity entity, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createEquipable(simpleVOFactory.createSimpleViewObject(position, "Hands/" + color + "/hand.xml"), slot, equipableItemVOFactory, entity, color));

    }

    public MicroPositionableViewObject createFoot(Position position, GameColor color) {
        return simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createSimpleViewObject(position, "Feet/" + color + "/Foot.xml"));
    }

    public HominidViewObject createHominid(Position position, Entity entity, ViewObject body, ViewObject head, HandsViewObject hands, FeetViewObject feet, BuffRingViewObject buffs){
        return simpleVOFactory.createHominid(position, entity, body, head, hands, feet, buffs, jumpDetector);
    }


    public HUDDecorator createHominidHUDDecorator(HominidViewObject hominidViewObject, Stats stats) {
        return simpleVOFactory.createHUDDecorator(hominidViewObject, stats);
    }

    public <T extends Moveable & ViewObservable> BipedMovingViewObject createBipedMovingObjectWithCharacterSubject(T subject, ViewObject child) {
        return simpleVOFactory.createBipedMovingViewObject(subject, child, jumpDetector);
    }

    private <T extends Directional & ViewObservable> DirectionalViewObject createBody(Position p, T d, String entityName) {
        return simpleVOFactory.createDirectional(p, d, "Entities/" +  entityName + "/");
    }





}
