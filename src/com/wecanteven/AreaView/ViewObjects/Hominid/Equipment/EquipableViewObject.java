package com.wecanteven.AreaView.ViewObjects.Hominid.Equipment;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.NullViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
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

    private Entity entitySubject;

    public EquipableViewObject(ViewObject child, ViewObject defaultEquipment, EquipmentSlot subject, ViewObjectFactory factory, Entity entitySubject) {
        super(child);
        this.defaultEquipment = defaultEquipment;
        this.subject = subject;
        this.entitySubject = entitySubject;
        this.factory = factory;
        update();
    }

    @Override
    public void setPosition(Position p) {
        super.setPosition(p);
        if(equipment != null)
            equipment.setPosition(p);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if(equipment != null)
            equipment.draw(g);
    }

    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        super.addToFogOfWarViewObject(fogOfWarViewObject);
        if(equipment != null)
            equipment.addToFogOfWarViewObject(fogOfWarViewObject);
    }

    @Override
    public void update() {
        if(!hasSubject())
            return;
        if (subject.hasItem()) {
            System.out.println("EQUIPING: " + subject.getItem().getName());
            equipment = factory.createEquipment(getPosition(),entitySubject, subject.getItem().getName());
        } else {
            if(hasEquipment()) {
                equipment = defaultEquipment;
                defaultEquipment.setPosition(getPosition());
            }
        }
    }

    private boolean hasSubject() {
        return subject != null;
    }

    private boolean hasEquipment() {
        return equipment != null;
    }
}
