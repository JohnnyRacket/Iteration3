package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Directional;
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
    private static final double HEX_WIDTH = 56d;
    private static final double HEX_LENGTH = 48d;
    private static final double HEX_HEIGHT = 15d;

    private double offsetAngle;

    private double height;

    private double tangent;

    private double radius;




    public MicroPositionableViewObject(ViewObject viewObject) {
        super(viewObject);
        this.direction = Direction.SOUTH;
        this.position = viewObject.getPosition().copy();
        this.offsetPosition = new Position(0,0,0);
        this.offsetAngle = 0D;
        this.height = 0D;
        this.tangent = 0D;
        this.radius = 0D;
    }

    public MicroPositionableViewObject(Direction direction, Position position, Position offsetPosition, ViewObject viewObject, double offsetAngle, double height, double tangent) {
        super(viewObject);
        this.direction = direction;
        this.position = position;
        this.offsetPosition = offsetPosition;
        this.offsetAngle = offsetAngle;
        this.height = height;
        this.tangent = tangent;
        this.radius = radius;
    }

    public MicroPositionableViewObject(Direction direction, Position position, Position offsetPosition, ViewObject viewObject, double offsetAngle, double height, double tangent, double radius) {
        super(viewObject);
        this.direction = direction;
        this.position = position;
        this.offsetPosition = offsetPosition;
        this.offsetAngle = offsetAngle;
        this.height = height;
        this.tangent = tangent;
        this.radius = radius;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        updatePositionOffset();
    }

    public void setTangent(double tangent) {
        this.tangent = tangent;
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
        position = p.copy();
        updateChildPosition();
    }

    private void updatePositionOffset() {

        offsetPosition.setR(getR());
        offsetPosition.setS(getS());
        offsetPosition.setZ(getZ());
        //System.out.println("Position offset did just change: " + offsetPosition);
        updateChildPosition();
    }


    private void updateChildPosition() {
        getChild().setPosition(position.add(offsetPosition));
    }

    public double getX() {
        return (int)(50*(radius*Math.cos(offsetAngle + direction.getAngle()) + tangent *Math.cos(Math.PI/2- offsetAngle - direction.getAngle()))) ;
    }

    public double getY() {
        return (int)(50*Math.tan(Config.TILT_ANGLE)*((radius*Math.sin(offsetAngle + direction.getAngle()) + tangent *Math.sin(Math.PI/2 - offsetAngle - direction.getAngle()))));
    }

    private double getZ() {
        return height/3*2;
    }

    private double getR() {
        return getX()/HEX_WIDTH;
    }

    private double getS() {
        return (0 - getY() - getR() * (HEX_LENGTH/2)) / HEX_LENGTH;
    }
}
