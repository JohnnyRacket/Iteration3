package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.UtilityClasses.Direction;
import javafx.geometry.Pos;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class HominidViewObject implements ViewObject {
    private Position position;
    private Direction direction;
    private DirectionalViewObject body;

    public HominidViewObject(Position position, Direction direction, DirectionalViewObject body) {
        this.position = position;
        this.direction = direction;
        this.body = body;

        direction.setDirectionOf(body);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
    }

    @Override
    public void draw(Graphics2D g) {
        body.draw(g);
    }
}
