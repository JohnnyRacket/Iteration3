package com.wecanteven.AreaView.ViewObjects.LeafVOs;


import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

/**
 * Created by alexs on 3/29/2016.
 */
public abstract class LeafViewObject implements ViewObject {
    private Position position;
    public LeafViewObject(Position position) {
        this.position = position;
    }


    @Override
    public final Position getPosition() {
        return position;
    }
    @Override
    public final void setPosition(Position p) {
        this.position = p;

    }
}
