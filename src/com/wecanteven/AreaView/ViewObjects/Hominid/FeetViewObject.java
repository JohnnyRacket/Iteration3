package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetFallingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetJumpingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.FeetWalkingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.LimbStrategy;
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

    private LimbStrategy walkingStrategy;
    private LimbStrategy fallingStartegy;
    private LimbStrategy jumpingStrategy;

    public FeetViewObject(Direction direction, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        changeDirection(direction);
        walkingStrategy = new FeetWalkingStrategy(0.3, leftFoot, rightFoot);
        fallingStartegy = new FeetJumpingStrategy(height, 2, leftFoot, rightFoot);//new FeetFallingStrategy(height, 5, leftFoot, rightFoot);
        jumpingStrategy = new FeetJumpingStrategy(height, 2, leftFoot, rightFoot);
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

    public void move(long duration) {
        walkingStrategy.execute(duration);
    }

    public void fall(long duration) {
        fallingStartegy.execute(duration);
    }

    public void jump(long duration) {
        jumpingStrategy.execute(duration);
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
