package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;

/**
 * Created by alexs on 4/13/2016.
 */
public class TundraFactory implements BiomeFactory {
    private SimpleVOFactory factory;

    public TundraFactory(SimpleVOFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimpleViewObject createAboveGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/ShallowSnow.xml");
    }

    @Override
    public SimpleViewObject createWater(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Water.xml");
    }

    @Override
    public SimpleViewObject createBelowGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Stone.xml");
    }

}
