package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.Factories.HandStateFactory;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

import java.awt.*;

/**
 * Created by adamfortier on 4/17/16.
 */
public class WingsViewObject extends HandsViewObject {
    public WingsViewObject(Position position, EquipmentSlot subject, HandStateFactory factory, Entity entity, GameColor color) {
        super(position, subject, factory, entity, color);
    }

    @Override
    public void drawForeground(Graphics2D graphic) {
        super.drawForeground(graphic);
    }

    @Override
    public void drawBackground(Graphics2D graphic) {
        super.drawBackground(graphic);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(Position p) {
        super.setPosition(p);
    }

    @Override
    public void changeDirection(Direction direction) {
        super.changeDirection(direction);
    }

    @Override
    public void draw(Graphics2D graphic) {
        super.draw(graphic);
    }

    @Override
    public void attack(long windUp, long coolDown) {
        super.attack(windUp, coolDown);
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        super.addToFogOfWarViewObject(parallelViewObject);
    }

    @Override
    public void move(long duration) {
        super.move(duration);
    }

    @Override
    public void jump(long duration) {
        super.jump(duration);
    }

    @Override
    public void fall(long duration) {
        super.fall(duration);
    }

    @Override
    public void update() {
        if (getEquipmentSlot().getItem() == null) {
            swapHandsState(getFactory().createWingState(getPosition(), getEquipmentSlot(), getEntity(), GameColor.BLUE));
        } else {
            getEquipmentSlot().getItem().accept(getWeaponsVisitor());
        }
    }

    @Override
    public void swapHandsState(HandState handState) {
        super.swapHandsState(handState);
    }
}
