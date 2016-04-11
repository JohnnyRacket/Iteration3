package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.Visitors.WeaponsVisitor;

import java.awt.*;

public class HandsViewObject implements ViewObject, Observer {
    private HandState handState;
    private Position position;
    private EquipmentSlot subject;
    private ViewObjectFactory factory;
    private Entity entity;
   // private Equipment --dont have it yet..



    public HandsViewObject(MicroPositionableViewObject leftHand,
                           MicroPositionableViewObject rightHand, Direction direction,
                           Position position, EquipmentSlot subject, ViewObjectFactory factory, Entity entity) {
        this.position = position;
        this.subject = subject;
        this.factory = factory;
        this.entity = entity;
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



    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        handState.addBackgroundToFogOfWar(fogOfWarViewObject);
        handState.addForegroundToFogOfWar(fogOfWarViewObject);
    }

    public void move(long duration) {
        handState.move(duration);
    }
    public void jump(long duration) { handState.jump(duration); }
    public void fall(long duration) {
        handState.fall(duration);
    }

    public void update() {
        subject.getItem();
    }


    public void swapHandsState(HandState handState) {
        this.handState = handState;
    }

    private class viewWeaponVisitor implements WeaponsVisitor{

        @Override
        public void visitOneHandedWeapon(OneHandedWeapon oneHandedWeapon) {
            swapHandsState(factory.createOneHandedWeaponState(getPosition(), handState.getDirection(), oneHandedWeapon.getName(), entity));
        }

        @Override
        public void visitOneHandedMeleeWeapon(OneHandedMeleeWeapon oneHandedMeleeWeapon) {
            handState.equip(oneHandedMeleeWeapon);
        }

        @Override
        public void visitOneHandedRangedWeapon(OneHandedRangedWeapon oneHandedRangedWeapon) {
            handState.equip(oneHandedRangedWeapon);
        }

        @Override
        public void visitWeapon(WeaponEquipableItem weapon) {
            handState.equip(weapon);
        }
    }

}
