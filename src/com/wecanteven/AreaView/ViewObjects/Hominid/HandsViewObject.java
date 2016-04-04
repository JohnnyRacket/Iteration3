package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public class HandsViewObject implements ViewObject, Observer{
    private HandState handState;
    private Position position;
   // private Equipment --dont have it yet..


    public HandsViewObject(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand, Position position) {
        this.position = position;
        handState = new BrawlingState(leftHand, rightHand);
    }

    public void drawForeground(Graphics2D graphic) {
        handState.drawForeground(graphic);
    }

    public void drawBackground(Graphics2D graphic) {
        handState.drawBackground(graphic);
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        handState.setHandsPosition(p);
    }

    public void draw(Graphics2D graphic) {
        handState.draw(graphic);
    }

    public void move(Graphics2D graphic) {
        handState.move(graphic);
    }

    public void changeDirection(Direction direction) {
        handState.changeDirection(direction);
    }

    public void update() {
        //TODO
    }

    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }
}
