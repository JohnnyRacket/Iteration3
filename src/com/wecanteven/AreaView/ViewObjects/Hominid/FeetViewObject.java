package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Observable;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by adamfortier on 4/6/16.
 */
public class FeetViewObject implements ViewObject {

    private  MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;

    private final double tangent = 0D;
    private final double radius = 0.25d;
    private final double leftAngle = Math.PI/2;
    private final double rightAngle = -leftAngle;
    private final double height = 0;
    private Position position;

    public FeetViewObject(Direction direction, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        changeDirection(direction);
        this.leftFoot.setRadius(radius);
        this.leftFoot.setOffsetAngle(leftAngle);
        this.leftFoot.setTangent(tangent);
        this.leftFoot.setHeight(height);
        this.rightFoot.setRadius(radius);
        this.rightFoot.setOffsetAngle(rightAngle);
        this.rightFoot.setTangent(tangent);
        this.rightFoot.setHeight(height);
    }

    public void changeDirection(Direction direction) {
        leftFoot.setDirection(direction);
        rightFoot.setDirection(direction);
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        updatePosition();
    }

    private void updatePosition() {
        leftFoot.setPosition(position);
        rightFoot.setPosition(position);
    }

    public void draw(Graphics2D g) {
        leftFoot.draw(g);
        rightFoot.draw(g);
        //feet.draw(g);
    }
}
