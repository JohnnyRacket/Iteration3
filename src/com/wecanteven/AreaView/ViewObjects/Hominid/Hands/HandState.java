package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;


import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.WeaponEquipableItem;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

public abstract class HandState {
    protected MicroPositionableViewObject leftHand;
    protected MicroPositionableViewObject rightHand;


    protected HandState(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public final Direction getDirection() {
        return leftHand.getDirection();
    }

    public abstract void move(long duration);

    public abstract void jump(long duration);

    public final void changeDirection(Direction direction) {
        leftHand.setDirection(direction);
        rightHand.setDirection(direction);
    }

    public abstract void attack(long windUp, long coolDown);

    public abstract void fall(long duration);

    public final double getLeftHandY() {
        return leftHand.getY();
    }

    public final double getRightHandY() {
        return rightHand.getY();
    }


    public final void setLeftHandPosition(Position position) {
        leftHand.setPosition(position);
    }

    public final void setRightHandPosition(Position position) {
        rightHand.setPosition(position);
    }

    public final void setLeftHandDirection(Direction direction) {
        leftHand.setDirection(direction);
    }

    public final void setRightHandDirection(Direction direction) {
        rightHand.setDirection(direction);
    }
    public final void equip(WeaponEquipableItem weapon) {

    }

    public final void draw(Graphics2D g) {
        leftHand.draw(g);
        rightHand.draw(g);
    }

    public final void drawBackground(Graphics2D g) {
        if (isInBackground(leftHand)) {
            leftHand.draw(g);
        }
        if (isInBackground(rightHand)) {
            rightHand.draw(g);
        }
    }

    public final void drawForeground(Graphics2D g) {
        if (!isInBackground(leftHand)) {
            leftHand.draw(g);
        }
        if (!isInBackground(rightHand)) {
            rightHand.draw(g);
        }
    }

    public final void addBackgroundToFogOfWar(ParallelViewObject parallelViewObject) {
        if (isInBackground(leftHand)) {
            leftHand.addToFogOfWarViewObject(parallelViewObject);
        }
        if (isInBackground(rightHand)) {
            rightHand.addToFogOfWarViewObject(parallelViewObject);
        }
    }

    public final void addForegroundToFogOfWar(ParallelViewObject parallelViewObject) {
        if (!isInBackground(leftHand)) {
            leftHand.addToFogOfWarViewObject(parallelViewObject);
        }
        if (!isInBackground(rightHand)) {
            rightHand.addToFogOfWarViewObject(parallelViewObject);
        }
    }

    private boolean isInBackground(MicroPositionableViewObject hand) {
        return hand.getY() > 0;
    }
}
