package com.wecanteven.AreaView.ViewObjects.Hominid.Feet;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Directionalizable;

import java.awt.*;

/**
 * Created by Alex on 4/2/2016.
 */
public class FeetViewObject implements ViewObject {
    private FootViewObject leftFoot;
    private FootViewObject rightFoot;

    private Position position;

    public FeetViewObject(FootViewObject leftFoot, FootViewObject rightFoot, Position position, Direction direction) {
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        this.position = position;

        leftFoot.setRadius(1);
        rightFoot.setRadius(-1);
        direction.setDirectionOf(leftFoot);
        direction.setDirectionOf(rightFoot);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
        leftFoot.setPosition(p);
        rightFoot.setPosition(p);
    }

    @Override
    public void draw(Graphics2D g) {
        leftFoot.draw(g);
        rightFoot.draw(g);
    }

    public void setDirection(Direction direction) {
        direction.setDirectionOf(leftFoot);
        direction.setDirectionOf(rightFoot);
    }


}
