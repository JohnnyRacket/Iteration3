package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.VisibilitySourceViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.NullViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by adamfortier on 4/16/16.
 */
public class SimpleVOFactory {
    private HexDrawingStrategy hexDrawingStrategy;
    private AreaView areaView;


    public  SimpleVOFactory(HexDrawingStrategy hexDrawingStrategy, AreaView areaView) {
        this.hexDrawingStrategy = hexDrawingStrategy;
        this.areaView = areaView;
    }

    public EquipableViewObject createEquipable(ViewObject child, ViewObject equipment, EquipableItemVOFactory equipableItemVOFactory, EquipmentSlot subject, Entity entity, GameColor color) {
        return new EquipableViewObject(child, equipment, subject, equipableItemVOFactory, entity, color);
    }

    public NullViewObject createNullViewObject() {
        return new NullViewObject(new Position(0,0,0));
    }

    public void setCenter(ViewObject center) {
        hexDrawingStrategy.setCenterTarget(center);
    }

    public <T extends Positionable & ViewObservable> void makeLightSource(ViewObject v, int radius, T subject) {

        new VisibilitySourceViewObject(v, subject, areaView, radius);
    }

    public <T extends Directional & ViewObservable> DirectionalViewObject createDirectional(Position p, T d, String path) {
        SimpleDynamicImage bodyNorth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "north.xml");
        SimpleDynamicImage bodySouth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "south.xml");
        SimpleDynamicImage bodyNorthEast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northeast.xml");
        SimpleDynamicImage bodyNorthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northwest.xml");
        SimpleDynamicImage bodySoutheast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southeast.xml");
        SimpleDynamicImage bodySouthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southwest.xml");
        return new DirectionalViewObject(p, d, hexDrawingStrategy, bodyNorth, bodySouth, bodyNorthEast, bodyNorthWest, bodySoutheast, bodySouthWest);
    }

}
