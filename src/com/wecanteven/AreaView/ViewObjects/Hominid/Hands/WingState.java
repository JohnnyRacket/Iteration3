package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.HandsYJumpingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.LimbStrategy;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Alex on 4/15/2016.
 */
public class WingState extends HandState {
    private LimbStrategy flyingStrategy;
    private LimbStrategy idleStrategy;
    private final double tangent = 0d;
    private final double radius = 0.45d;
    private final double leftAngle = Math.PI/2;
    private final double rightAngle = -leftAngle;
    private final double height = 1.1d;
    private boolean onGround = true;
    public WingState(Direction direction, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        super(leftHand, rightHand);
        this.flyingStrategy = new HandsYJumpingStrategy(height, 3, leftHand, rightHand);
        this.idleStrategy = new HandsYJumpingStrategy(height, 3, leftHand, rightHand);


        changeDirection(direction);
        leftHand.setRadius(radius);
        leftHand.setOffsetAngle(leftAngle);
        leftHand.setTangent(tangent);
        leftHand.setHeight(height);
        rightHand.setRadius(radius);
        rightHand.setOffsetAngle(rightAngle);
        rightHand.setTangent(tangent);
        rightHand.setHeight(height);

        ViewTime.getInstance().register( this::idle, 3000);
    }

    @Override
    public void move(long duration) {
        onGround = true;
        idleStrategy.stop();
        flyingStrategy.execute(duration);
    }

    @Override
    public void jump(long duration) {
        onGround = false;
        idleStrategy.stop();
        flyingStrategy.execute(duration);
    }



    @Override
    public void attack(long durationOfAttack) {

    }

    @Override
    public void fall(long duration) {

    }

    private void idle() {
        if (!flyingStrategy.isExecuting() && !idleStrategy.isExecuting() && !onGround) {
            idleStrategy.execute(750);
        }
        ViewTime.getInstance().register( this::idle, 500);

    }
}
