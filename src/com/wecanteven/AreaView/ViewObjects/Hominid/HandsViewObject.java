package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public class HandsViewObject implements ViewObject, Observer {
    private HandState handState;
    private Position position;
    private EquipmentSlot subject;
   // private Equipment --dont have it yet..



    public HandsViewObject(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand, Direction direction, Position position, EquipmentSlot subject) {
        this.position = position;
        this.subject = subject;
        handState = new BrawlingState(direction, leftHand, rightHand);
    }

    public void drawForeground(Graphics2D graphic) {
        if (handState.getLeftHandY() < 0) {
            handState.drawLeftHand(graphic);
        }
        if (handState.getRightHandY() < 0) {
            handState.drawRightHand(graphic);
        }
    }

    public void drawBackground(Graphics2D graphic) {
        if (handState.getLeftHandY() >= 0) {
            handState.drawLeftHand(graphic);
        }
        if (handState.getRightHandY() >= 0) {
            handState.drawRightHand(graphic);
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        handState.setRightHandPosition(p);
        handState.setLeftHandPosition(p);
    }

    public void changeDirection(Direction direction) {
        handState.setLeftHandDirection(direction);
        handState.setRightHandDirection(direction);

    }

    public void draw(Graphics2D graphic) {
        drawBackground(graphic);
        drawForeground(graphic);
    }

    public void move(long duration) {
        handState.move(duration);
    }
    public void jump(long duration) { handState.jump(duration); }
    public void fall(long duration) {
        handState.fall(duration);
    }

    public void update() {
        //TODO
    }


    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }
}
