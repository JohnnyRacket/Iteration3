package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;

/**
 * Created by alexs on 4/12/2016.
 */
public interface BiomeFactory {
    SimpleViewObject createAboveGround(Position p);
    SimpleViewObject createWater(Position p);
    SimpleViewObject createBelowGround(Position p);
}
