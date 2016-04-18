package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.*;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 4/17/2016.
 */
public class TwoHandState extends HandState {
    private final double tangent = 0d;
    private final double radius = 0.5d;
    private final double leftAngle = -Math.PI/2;
    private final double rightAngle = leftAngle;
    private final double leftHeight = 2.5d;
    private final double rightHeight = 1.5d;

    private LimbStrategy walkingStrategy;
    private LimbStrategy jumpingStrategy;
    private LimbStrategy fallingStrategy;

    private LimbStrategy clubingStrategy;
    private RetractingStrategy retractingStrategy;

    public TwoHandState(Direction direction, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        super(leftHand, rightHand);
        changeDirection(direction);
        leftHand.setRadius(radius);
        leftHand.setOffsetAngle(leftAngle);
        leftHand.setTangent(tangent);
        leftHand.setHeight(leftHeight);
        rightHand.setRadius(radius);
        rightHand.setOffsetAngle(rightAngle);
        rightHand.setTangent(tangent);
        rightHand.setHeight(rightHeight);
        walkingStrategy = new HandWalkingStrategy(0.3, leftHand, rightHand);
        jumpingStrategy = new HandsYJumpingStrategy(leftHeight, rightHeight, 5, leftHand, rightHand);
        fallingStrategy = new HandsFallingStrategy(leftHeight, rightHeight, radius, leftHand, rightHand);
        clubingStrategy = new ClubingStategy(leftHand, rightHand);
        retractingStrategy = new RetractingStrategy(leftHand, rightHand);
    }

    @Override
    public void move(long duration) {
        walkingStrategy.execute(duration);
    }

    @Override
    public void jump(long duration) {
        jumpingStrategy.execute(duration);
    }

    @Override
    public void attack(long windUp, long coolDown) {
        clubingStrategy.execute(windUp);
        ViewTime.getInstance().register(() -> retractingStrategy.execute(coolDown), windUp);
    }

    @Override
    public void fall(long duration) {
        fallingStrategy.execute(duration);
    }
}
