package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class HominidViewObject implements ViewObject, Observer{
    private Position position;
    private Direction direction;
    private DirectionalViewObject body;
    private Directional directionSubject;

    public HominidViewObject(Position position, Direction direction, Directional directionSubject, DirectionalViewObject body) {
        this.position = position;
        this.direction = direction;
        this.directionSubject = directionSubject;
        this.body = body;

        direction.setDirectionOf(body);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        this.body.setPosition(p);
        this.position = p;
    }

    @Override
    public void draw(Graphics2D g) {
        body.draw(g);
    }

    @Override
    public void update() {
        this.direction = directionSubject.getDirection();
        this.direction.setDirectionOf(body);
    }
}
