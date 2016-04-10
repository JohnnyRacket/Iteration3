package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;

import java.awt.*;

/**
 * Created by Alex on 4/4/2016.
 */
public class NullViewObject extends LeafViewObject {
    @Override
    public void draw(Graphics2D g) {
        //That's right. Mixed Instance is 100% OK here.
    }

    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        //Mixed Instance is ok sometimes
    }

    public NullViewObject(Position position) {
        super(position);
    }
}
