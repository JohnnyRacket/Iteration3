package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public class HandsViewObject implements ViewObject, Observer{
    private HandState handState;
    private Position position;
   // private Equipment --dont have it yet..

    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;



    public HandsViewObject(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand, Direction direction, Position position) {
        this.position = position;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        handState = new BrawlingState(direction, leftHand, rightHand);
    }

    public void drawForeground(Graphics2D graphic) {
        handState.drawForeground(graphic);
    }

    public void drawBackground(Graphics2D graphic) {
        handState.drawBackground(graphic);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        rightHand.setPosition(p);
        leftHand.setPosition(p);
    }

    public void changeDirection(Direction direction) {
        leftHand.setDirection(direction);
        rightHand.setDirection(direction);
    }

    public void draw(Graphics2D graphic) {
        handState.draw(graphic);
    }

    public void move(Graphics2D graphic) {
        handState.move(graphic);
    }

    public void update() {
        //TODO
    }

    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }
}
