package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by adamfortier on 4/4/16.
 */
public class MicroPositionableViewObject extends DecoratorViewObject {
    private Direction direction;
    private Position position;
    private Position offsetPosition;
    private final double CAMERA_TILT_FACTOR = Config.TILT_ANGLE;

    private double offsetAngle;

    private double height;

    private double tagent;

    private double radius;



    public MicroPositionableViewObject(ViewObject viewObject) {
        super(viewObject);
        this.direction = Direction.SOUTH;
        this.position = viewObject.getPosition().copy();
        this.offsetPosition = new Position(0,0,0);
        this.offsetAngle = 0D;
        this.height = 0D;
        this.tagent = 0D;
        this.radius = 0D;
    }

    public MicroPositionableViewObject(Direction direction, Position position, Position offsetPosition, ViewObject viewObject, double offsetAngle, double height, double tangent, double radius) {
        super(viewObject);
        this.direction = direction;
        this.position = position;
        this.offsetPosition = offsetPosition;
        this.offsetAngle = offsetAngle;
        this.height = height;
        this.tagent = tangent;
        this.radius = radius;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        updatePositionOffset();
    }

    public void setTagent(double tagent) {
        this.tagent = tagent;
        updatePositionOffset();
    }

    public void setHeight(double height) {
        this.height = height;
        updatePositionOffset();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        updatePositionOffset();
    }

    public void setOffsetAngle(double offsetAngle) {
        this.offsetAngle = offsetAngle;
        updatePositionOffset();
    }

    @Override

    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        updateChildPosition();
    }

    private void updatePositionOffset() {
        setROffset();
        setSOffset();
        setZOffset();
        updateChildPosition();
    }


    private void updateChildPosition() {
        getChild().setPosition(position.add(offsetPosition));
    }

    private void setZOffset() {
        offsetPosition.setZ(height);
    }

    private void setSOffset() {
        offsetPosition.setS(radius*Math.sin(offsetAngle) + tagent*Math.sin(offsetAngle + Math.PI/2));
    }

    private void setROffset() {
        offsetPosition.setR(1/Math.cos(Config.TILT_ANGLE)*(radius*Math.cos(offsetAngle) + tagent*Math.cos(Math.PI/2- offsetAngle)));

    }
}
