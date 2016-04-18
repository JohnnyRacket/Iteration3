package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.*;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.OneHandedWeaponState;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.TwoHandState;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by alexs on 4/17/2016.
 */
public class HandStateFactory {
    private HominidVOFactory hominidVOFactory;
    private EquipableItemVOFactory equipableItemVOFactory;
    private SimpleVOFactory simpleVOFactory;

    public HandStateFactory(HominidVOFactory hominidVOFactory, EquipableItemVOFactory equipableItemVOFactory, SimpleVOFactory simpleVOFactory) {
        this.hominidVOFactory = hominidVOFactory;
        this.equipableItemVOFactory = equipableItemVOFactory;
        this.simpleVOFactory = simpleVOFactory;
    }

    public BrawlingState createFistState(Position p, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        MicroPositionableViewObject leftHand = hominidVOFactory.createWeaponHand(p, slot, weaponSubject, color);
        MicroPositionableViewObject rightHand = hominidVOFactory.createWeaponHand(p, slot, weaponSubject, color);
        return new BrawlingState(weaponSubject.getDirection(), leftHand, rightHand);
    }

    public OneHandedWeaponState createOneHandState(Position p, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        MicroPositionableViewObject rightHand = hominidVOFactory.createHand(p, color);
        MicroPositionableViewObject leftHand = hominidVOFactory.createWeaponHand(p, slot, weaponSubject, color);
        return new OneHandedWeaponState(weaponSubject.getDirection(), leftHand, rightHand);
    }

    public TwoHandState createTwoHandState(Position p, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        MicroPositionableViewObject rightHand = hominidVOFactory.createHand(p, color);
        MicroPositionableViewObject leftHand = hominidVOFactory.createWeaponHand(p, slot, weaponSubject, color);
        return new TwoHandState(weaponSubject.getDirection(), leftHand, rightHand);
    }

    public WingState createWingState(Position position, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        MicroPositionableViewObject leftWing = hominidVOFactory.createWing(position, slot, weaponSubject, color);
        MicroPositionableViewObject rightWing = hominidVOFactory.createWing(position, slot, weaponSubject, color);
        return new WingState(weaponSubject.getDirection(), leftWing, rightWing);
    }

    public AnimatedHandState createAnimatedHandState(Position p, EquipmentSlot slot, Entity weaponSubject, GameColor color) {
        StartableViewObject left = equipableItemVOFactory.createStartableEquipment(p,slot.getItem().getName(), color);
        StartableViewObject right = equipableItemVOFactory.createStartableEquipment(p,slot.getItem().getName(), color);
        MicroPositionableViewObject leftHand = simpleVOFactory.createMicroPositionableViewObject(left);
        MicroPositionableViewObject rightHand = simpleVOFactory.createMicroPositionableViewObject(right);
        return new AnimatedHandState(weaponSubject.getDirection(), leftHand, rightHand, left, right);
    }
}
