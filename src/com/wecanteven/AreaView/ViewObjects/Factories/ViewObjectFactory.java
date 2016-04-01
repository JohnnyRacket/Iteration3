package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.HandViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Observers.Directional;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 3/31/2016.
 */
public abstract class ViewObjectFactory {
    private HexDrawingStrategy hexDrawingStrategy = new HexDrawingStrategy();
    private AreaView areaView;
    private DynamicImageFactory factory = DynamicImageFactory.getInstance();

    public ViewObjectFactory(AreaView areaView) {
        this.areaView = areaView;
    }

    public abstract ViewObject createGround(Position p);
    public abstract ViewObject createWater(Position p);

    public ViewObject createSneak(Position p, Direction d, Entity subject) {
        DirectionalViewObject body = createBody(p, d, "Sneak");


        HandsViewObject hands = new HandsViewObject(new HandViewObject(p, 1, 1, 1, Math.PI/3, hexDrawingStrategy), new HandViewObject(p,  0.5, 0.5, 0.5, 0.5, hexDrawingStrategy), p);
        HominidViewObject stationarySneak = new  HominidViewObject(p, d, subject, body, hands);
        subject.attach(stationarySneak);
        return createMovingViewObject(subject, stationarySneak);

    }

    private DirectionalViewObject createBody(Position p, Direction d, String entityName) {
        DynamicImage bodyNorth = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/north.xml");
        DynamicImage bodySouth = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/south.xml");
        DynamicImage bodyNorthEast = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/northeast.xml");
        DynamicImage bodyNorthWest = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/northwest.xml");
        DynamicImage bodySoutheast = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/southeast.xml");
        DynamicImage bodySouthWest = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/southwest.xml");
        return new DirectionalViewObject(p, d, hexDrawingStrategy, bodyNorth, bodySouth, bodyNorthEast, bodyNorthWest, bodySoutheast, bodySouthWest);
    }

    private MovingViewObject createMovingViewObject(Entity subject, ViewObject child) {
        MovingViewObject mvo = new  MovingViewObject(child, subject, areaView);
        subject.attach(mvo);
        return mvo;
    }

    public HexDrawingStrategy getDrawingStrategy() {
        return hexDrawingStrategy;
    }

    public DynamicImageFactory getDynamicImageFactory() {
        return factory;
    }

    protected SimpleViewObject createSimpleViewObject(Position p, String path) {
        return new SimpleViewObject(p, factory.loadDynamicImage(path), hexDrawingStrategy);
    }
}
