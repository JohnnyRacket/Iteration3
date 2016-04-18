package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.HandStateFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.*;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Sound;
import com.wecanteven.Visitors.WeaponsVisitor;

import java.awt.*;

public class HandsViewObject implements ViewObject, Observer {
    private HandState handState;
    private Position position;
    private EquipmentSlot subject;
    private HandStateFactory factory;
    private WeaponsVisitor weaponsVisitor = new ViewWeaponVisitor();
    private Entity entity;
   // private Equipment --dont have it yet..



    private GameColor color;

    public HandsViewObject(Position position,
                           EquipmentSlot subject,
                           HandStateFactory factory,
                           Entity entity,
                           GameColor color) {
        this.position = position;
        this.subject = subject;
        this.factory = factory;
        this.color = color;
        this.entity = entity;
        registerToSubject();

        update();
    }

    private void registerToSubject() {
        subject.attach(this);
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

    public void attack(long windUp, long coolDown) {
        handState.attack(windUp, coolDown);
    }


    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        handState.addBackgroundToFogOfWar(parallelViewObject);
        handState.addForegroundToFogOfWar(parallelViewObject);
    }

    public EquipmentSlot getEquipmentSlot() {
        return subject;
    }

    public Entity getEntity() {
        return entity;
    }

    public GameColor getColor() {
        return color;
    }

    public WeaponsVisitor getWeaponsVisitor() {
        return weaponsVisitor;
    }

    public void move(long duration) {
        handState.move(duration);
    }
    public void jump(long duration) { handState.jump(duration); }
    public void fall(long duration) {
        handState.fall(duration);
    }

    public void update() {
        if (subject.getItem() == null) {
            swapHandsState(factory.createFistState(position, subject, entity, color));
        } else {
            subject.getItem().accept(weaponsVisitor);
        }

    }

    public HandStateFactory getFactory() {
        return factory;
    }


    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }

    protected class ViewWeaponVisitor implements WeaponsVisitor{

        @Override
        public void visitFistWeapon(FistWeapon f) {
            swapHandsState(factory.createFistState(getPosition(), subject,  entity, color));
        }

        @Override
        public void visitOneHandWeapon(OneHandWeapon o) {
            swapHandsState(factory.createOneHandState(getPosition(), subject, entity, color));
        }

        @Override
        public void visitRangedWeapon(RangedWeaponEquipableItem r) {
            swapHandsState(factory.createFistState(getPosition(), subject, entity, color));
        }

        @Override
        public void visitStaveWeapon(StaveWeapon s) {
            swapHandsState(factory.createTwoHandState(getPosition(), subject, entity, color));
        }

        @Override
        public void visitTwoHandWeapon(TwoHandedWeapon t) {
            swapHandsState(factory.createTwoHandState(getPosition(), subject, entity, color));
        }
    }

}
