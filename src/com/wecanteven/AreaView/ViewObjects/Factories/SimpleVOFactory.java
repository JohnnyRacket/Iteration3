package com.wecanteven.AreaView.ViewObjects.Factories;

import com.sun.glass.ui.View;
import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.HUDDecorator;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.BipedMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving.SimpleMovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.VisibilitySourceViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.BuffRingViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.*;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

import java.awt.*;

/**
 * Created by adamfortier on 4/16/16.
 */
public class SimpleVOFactory {
    private HexDrawingStrategy hexDrawingStrategy;
    private AreaView areaView;
    private DynamicImageFactory dynamicImageFactory = DynamicImageFactory.getInstance();
    private EquipableItemVOFactory equipableItemVOFactory;
    private HominidVOFactory hominidVOFactory;
    private MapItemVOFactory mapItemVOFactory;

    public  SimpleVOFactory(HexDrawingStrategy hexDrawingStrategy, AreaView areaView, Map gameMap) {
        this.hexDrawingStrategy = hexDrawingStrategy;
        this.areaView = areaView;
        this.equipableItemVOFactory = new EquipableItemVOFactory(this);
        this.hominidVOFactory = new HominidVOFactory(this, equipableItemVOFactory, new JumpDetector(gameMap));
        this.mapItemVOFactory = new MapItemVOFactory(this);
    }

    public EquipableViewObject createEquipable(ViewObject child, EquipmentSlot slot, EquipableItemVOFactory equipableItemVOFactory, Entity weaponSubject, GameColor color) {
        return new EquipableViewObject(child, slot, equipableItemVOFactory, weaponSubject, color);
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

    public ViewObject createFloatingTextViewObject(Position position, String text, long duration, Color color, double distance) {
        return new FloatingTextViewObject(position, text, distance, duration, color, hexDrawingStrategy);
    }

    public StartableViewObject createStartableViewObject(Position p, String path) {
        StartableDynamicImage startableDynamicImage = dynamicImageFactory.loadActiveDynamicImage(path);
        return new StartableViewObject(p, startableDynamicImage, hexDrawingStrategy);
    }

    public SimpleViewObject createSimpleViewObject(Position p, String path) {
        return new SimpleViewObject(p, dynamicImageFactory.loadDynamicImage(path), hexDrawingStrategy);
    }

    public MicroPositionableViewObject createMicroPositionableViewObject(ViewObject child) {
        return new MicroPositionableViewObject(child);
    }


    public HominidViewObject createHominid(Position position, Entity entity, ViewObject body, ViewObject head, HandsViewObject hands, FeetViewObject feet, BuffRingViewObject buffs, JumpDetector jumpDetector){
        return new HominidViewObject(position, entity, body, head, hands, feet, buffs, jumpDetector);
    }



    public HUDDecorator createHUDDecorator(HominidViewObject hominidViewObject, Stats stats) {
        return new HUDDecorator(hominidViewObject, stats, hexDrawingStrategy, this, areaView);
    }



    public <T extends Moveable & ViewObservable> BipedMovingViewObject createBipedMovingViewObject(T subject, ViewObject child, JumpDetector jumpDetector) {
        BipedMovingViewObject mvo = new BipedMovingViewObject(child, subject, areaView, jumpDetector);
        return mvo;
    }

    public <T extends Moveable & ViewObservable> SimpleMovingViewObject createSimpleMovingViewObject(T subject, ViewObject child) {
        SimpleMovingViewObject mvo = new SimpleMovingViewObject(child, subject, areaView);
        return mvo;
    }


    public EquipableItemVOFactory getEquipableItemVOFactory() {
        return equipableItemVOFactory;
    }

    public HominidVOFactory getHominidVOFactory() {
        return hominidVOFactory;
    }

    public MapItemVOFactory getMapItemVOFactory() {
        return mapItemVOFactory;
    }


}
