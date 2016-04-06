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

    public void move(Graphics2D graphic) {
        //TODO
    }

    public void changeDirection(Direction direction) {
        //TODO
    }

    public void setLocation(Location location) {
        //TODO
    }

    public Location getLocation() {
        return null;
    }

    public void attack(long durationOfAttack) {
        //TODO
    }

    public void setHandsPosition(Position position) {
        this.leftHand.setPosition(position);
        this.rightHand.setPosition(position);
    }

    public void equip(/*add weapon param model doesnt exist*/) {

    }
}
