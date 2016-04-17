package com.wecanteven.AreaView.ViewObjects.Hominid.Equipment;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.ItemVOFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.GameColor;

import java.awt.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class EquipableViewObject extends DecoratorViewObject implements Observer {
    private ViewObject defaultEquipment;
    private ViewObject equipment;
    private EquipmentSlot subject;
    private ItemVOFactory factory;

    private Entity entitySubject;
    private GameColor color;

    public EquipableViewObject(ViewObject child, ViewObject defaultEquipment, EquipmentSlot subject, ItemVOFactory factory, Entity entitySubject, GameColor color) {
        super(child);
        this.defaultEquipment = defaultEquipment;
        this.subject = subject;
        this.entitySubject = entitySubject;
        this.factory = factory;
        this.color = color;

        //Attach this to subject
        subject.attach(this);
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
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        super.addToFogOfWarViewObject(parallelViewObject);
        if(equipment != null)
            equipment.addToFogOfWarViewObject(parallelViewObject);
    }

    @Override
    public void update() {
        if(!hasSubject())
            return;
        if (subject.hasItem()) {
            equipment = factory.createEquipment(getPosition(),entitySubject, subject.getItem().getName(), color);
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
