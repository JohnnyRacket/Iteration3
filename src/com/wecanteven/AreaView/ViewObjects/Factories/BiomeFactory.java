package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.Models.Decals.GroundDecal;
import com.wecanteven.Models.Decals.TreeDecal;
import javafx.geometry.Pos;

/**
 * Created by alexs on 4/12/2016.
 */
public interface BiomeFactory {
    SimpleViewObject createAboveGround(Position p);
    SimpleViewObject createWater(Position p);
    SimpleViewObject createBelowGround(Position p);
    DecalViewObject createTreeDecal(Position p, TreeDecal t);
    //DecalViewObject createGroundDecal(Position p, GroundDecal g);
    //DecalViewObject createFlowerDeca
}
