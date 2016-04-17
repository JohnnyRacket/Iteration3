package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.GameColor;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by adamfortier on 4/16/16.
 */
public class EquipableItemVOFactory {
    private SimpleVOFactory simpleVOFactory;

    public EquipableItemVOFactory(SimpleVOFactory viewObjectFactory) {
        this.simpleVOFactory = viewObjectFactory;
    }


    public EquipableViewObject createEquipable(ViewObject child, EquipmentSlot subject, Entity entity, GameColor color) {
        return new EquipableViewObject(child, subject, this, entity, color);
    }

    public <T extends Directional & ViewObservable> ViewObject createEquipment(Position p, T subject, String name, GameColor color) {
        //First we try to find a nondirectional equipment
        String path = "Equipment/" + color + "/" + name + ".xml";
        if (fileExists("resources/" + path))
            return simpleVOFactory.createSimpleViewObject(p, path);

        return  simpleVOFactory.createDirectional(p, subject, "Equipment/" +color.name + "/" + name + "/");

    }

    private boolean fileExists(String path) {
        return (new File(path)).exists();
    }

    public MicroPositionableViewObject createBuff(Position p, String name) {
        return simpleVOFactory.createMicroPositionableViewObject(simpleVOFactory.createSimpleViewObject(p, "Buffs/" + name + ".xml"));
    }
}
