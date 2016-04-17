package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by adamfortier on 4/17/16.
 */
public class MapItemVOFactory {
    private SimpleVOFactory simpleVOFactory;

    public MapItemVOFactory(SimpleVOFactory simpleVOFactory) {
        this.simpleVOFactory = simpleVOFactory;
    }

    public SimpleViewObject createAoe(Position position, String name) {
        return simpleVOFactory.createSimpleViewObject(position, "AreaOfEffects/" + name + ".xml");
    }
}
