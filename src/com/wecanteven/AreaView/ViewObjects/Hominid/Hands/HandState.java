package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;


import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.Models.Items.Takeable.Equipable.WeaponEquipableItem;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
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

    public Direction getDirection() {
        return leftHand.getDirection();
    }

    public abstract void move(long duration);

    public abstract void jump(long duration);

    public abstract void changeDirection(Direction direction);

    public abstract void setLocation(Location location);

    public abstract Location getLocation();

    public abstract void attack(long durationOfAttack);

    public abstract void fall(long duration);

    public double getLeftHandY() {
        return leftHand.getY();
    }

    public double getRightHandY() {
        return rightHand.getY();
    }


    public void setLeftHandPosition(Position position) {
        leftHand.setPosition(position);
    }

    public void setRightHandPosition(Position position) {
        rightHand.setPosition(position);
    }

    public void setLeftHandDirection(Direction direction) {
        leftHand.setDirection(direction);
    }

    public void setRightHandDirection(Direction direction) {
        rightHand.setDirection(direction);
    }
    public void equip(WeaponEquipableItem weapon) {

    }

    public void draw(Graphics2D g) {
        leftHand.draw(g);
        rightHand.draw(g);
    }

    public void drawBackground(Graphics2D g) {
        if (isInBackground(leftHand)) {
            leftHand.draw(g);
        }
        if (isInBackground(rightHand)) {
            rightHand.draw(g);
        }
    }

    public void drawForeground(Graphics2D g) {
        if (!isInBackground(leftHand)) {
            leftHand.draw(g);
        }
        if (!isInBackground(rightHand)) {
            rightHand.draw(g);
        }
    }

    public void addBackgroundToFogOfWar(FogOfWarViewObject fogOfWarViewObject) {
        if (isInBackground(leftHand)) {
            leftHand.addToFogOfWarViewObject(fogOfWarViewObject);
        }
        if (isInBackground(rightHand)) {
            rightHand.addToFogOfWarViewObject(fogOfWarViewObject);
        }
    }

    public void addForegroundToFogOfWar(FogOfWarViewObject fogOfWarViewObject) {
        if (!isInBackground(leftHand)) {
            leftHand.addToFogOfWarViewObject(fogOfWarViewObject);
        }
        if (!isInBackground(rightHand)) {
            rightHand.addToFogOfWarViewObject(fogOfWarViewObject);
        }
    }

    private boolean isInBackground(MicroPositionableViewObject hand) {
        return hand.getY() > 0;
    }
}
