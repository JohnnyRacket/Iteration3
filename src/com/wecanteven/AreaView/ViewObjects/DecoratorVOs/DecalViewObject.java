package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

/**
 * Created by alexs on 4/13/2016.
 */
public class DecalViewObject extends DecoratorViewObject{
    private Position offsetPosition;
    private Position position;
    public DecalViewObject(ViewObject child, double rOffset, double sOffset) {
        super(child);
        this.offsetPosition = new Position(rOffset, sOffset, 0);
        setPosition(child.getPosition());
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
        getChild().setPosition(p.add(offsetPosition));
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
