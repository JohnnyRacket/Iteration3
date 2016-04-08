package com.wecanteven.AreaView.ViewObjects.Hominid.Equipment;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.NullViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class EquipableViewObject extends DecoratorViewObject implements Observer {
    private ViewObject defaultEquipment;
    private ViewObject equipment;
    private EquipmentSlot subject;
    private ViewObjectFactory factory;

    private Directional directionalSubject;

    public EquipableViewObject(ViewObject child, ViewObject defaultEquipment, EquipmentSlot subject, ViewObjectFactory factory, Directional directionalSubject) {
        super(child);
        this.defaultEquipment = defaultEquipment;
        this.subject = subject;
        this.directionalSubject = directionalSubject;
        this.factory = factory;
        update();
    }

    @Override
    public void setPosition(Position p) {
        super.setPosition(p);
        equipment.setPosition(p);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        equipment.draw(g);
    }

    @Override
    public void update() {
        if (subject.hasItem()) {
            System.out.println("EQUIPING: " + subject.getItem().getName());
            equipment = factory.createEquipment(getPosition(),directionalSubject, subject.getItem().getName());
        } else {
            equipment = defaultEquipment;
        }

    }
}
