package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.BrawlingState;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandState;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.OneHandedWeaponState;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.TwoHandState;
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

    public HandStateFactory(HominidVOFactory hominidVOFactory) {
        this.hominidVOFactory = hominidVOFactory;
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
}
