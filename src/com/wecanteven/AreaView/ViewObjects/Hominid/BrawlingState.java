package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;


public class BrawlingState extends HandState {

    public BrawlingState(HandViewObject leftHand, HandViewObject rightHand) {
        super(leftHand, rightHand);
    }

    public void drawForeground(Graphics2D graphic) {
        //TODO
    }

    public void drawBackground(Graphics2D graphic) {
        //TODO
    }

    public void draw(Graphics2D graphic) {
        super.draw(graphic);
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

    public void equip(/*add weapon param model doesnt exist*/) {

    }

    //swapping states could be here
}
