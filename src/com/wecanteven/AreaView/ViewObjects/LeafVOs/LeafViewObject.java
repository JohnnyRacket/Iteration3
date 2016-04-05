package com.wecanteven.AreaView.ViewObjects.LeafVOs;


import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

/**
 * Created by alexs on 3/29/2016.
 */
public abstract class LeafViewObject implements ViewObject {
    private Position position = new Position(0,0,0);
    public LeafViewObject(Position position) {
        setPosition(position);
    }


    @Override
    public final Position getPosition() {
        return position;
    }
    @Override
    public final void setPosition(Position p) {
        position = p.copy();
    }
}
