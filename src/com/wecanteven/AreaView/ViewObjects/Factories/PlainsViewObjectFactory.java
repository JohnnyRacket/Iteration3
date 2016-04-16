package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Map.Map;

/**
 * Created by Alex on 3/31/2016.
 */
public class PlainsViewObjectFactory extends ViewObjectFactory {


    public PlainsViewObjectFactory(AreaView areaView, Map gameMap) {
        super(areaView, gameMap);
    }

    @Override
    public ViewObject createGround(Position p) {
        return createSimpleViewObject(p, "Terrain/Grass.xml");
    }

    @Override
    public ViewObject createWater(Position p) {
        return createSimpleViewObject(p, "Terrain/Water.xml");

    }

    @Override
    public ViewObject createCurrent(Position p) {
        return createSimpleViewObject(p, "Terrain/Current.xml");

    }

}
