package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.Models.Decals.TreeDecal;

/**
 * Created by alexs on 4/13/2016.
 */
public class AutumnFactory implements BiomeFactory {
    private ViewObjectFactory factory;

    public AutumnFactory(ViewObjectFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimpleViewObject createAboveGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/FallGrass.xml");
    }

    @Override
    public SimpleViewObject createWater(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Water.xml");
    }

    @Override
    public SimpleViewObject createBelowGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Stone.xml");
    }

    @Override
    public DecalViewObject createTreeDecal(Position p, TreeDecal t) {
        return factory.createDecalViewObject(p, t, "Decals/AutumnTree1.xml");
    }
}
