package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by adamfortier on 4/16/16.
 */
public class EquipableItemVOFactory {
    private SimpleVOFactory viewObjectFactory;

    public EquipableItemVOFactory(SimpleVOFactory viewObjectFactory) {
        this.viewObjectFactory = viewObjectFactory;
    }


    public EquipableViewObject createEquipable(ViewObject child, EquipmentSlot subject, Entity entity, GameColor color) {
        return new EquipableViewObject(child, subject, this, entity, color);
    }

    public ViewObject createEquipment(Position p, Entity entity, String name, GameColor color) {
        //First we try to find a nondirectional equipment
        try {
            return viewObjectFactory.createSimpleViewObject(p, "Equipment/" + color + "/" + name + ".xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        DirectionalViewObject directionalViewObject =  viewObjectFactory.createDirectional(p, entity, "Equipment/" +color.name + "/" + name + "/");
        entity.attach(directionalViewObject);
        return directionalViewObject;
    }
}
