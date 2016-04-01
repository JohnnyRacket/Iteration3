package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public class HandsViewObject {
    HandState handState;

    public HandsViewObject() {
        this.handState = new BrawlingState();
    }

    public HandsViewObject(HandState handState) {
        this.handState = handState;
    }

    public void drawForeground(Graphics2D graphic) {
        handState.drawForeground(graphic);
    }

    public void drawBackground(Graphics2D graphic) {
        handState.drawBackground(graphic);
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

    public void setLocation(Location location) {
        handState.setLocation(location);
    }

    public Location getLocation() {
        return handState.getLocation();
    }

    public void update() {
        //TODO
    }

    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }
}
