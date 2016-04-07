package com.wecanteven.AreaView.ViewObjects.Hominid;


import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public abstract class HandState {
    protected MicroPositionableViewObject leftHand;
    protected MicroPositionableViewObject rightHand;


    protected HandState(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public abstract void move(long duration);

    public abstract void jump(long duration);

    public abstract void changeDirection(Direction direction);

    public abstract void setLocation(Location location);

    public abstract Location getLocation();

    public abstract void attack(long durationOfAttack);

    public void equip(/*add weapon param model doesnt exist*/) {

    }
}
