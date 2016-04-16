package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandState;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.HandWalkingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.HandsFallingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.HandsYJumpingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.LimbStrategy;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;


public class BrawlingState extends HandState {

    private final double tangent = 0d;
    private final double radius = 0.55d;
    private final double leftAngle = Math.PI/2;
    private final double rightAngle = -leftAngle;
    private final double height = 1.5d;

    private LimbStrategy walkingStrategy;
    private LimbStrategy jumpingStrategy;
    private LimbStrategy fallingStrategy;

    public BrawlingState(Direction direction, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        super(leftHand, rightHand);
        walkingStrategy = new HandWalkingStrategy(0.3, leftHand, rightHand);
        jumpingStrategy = new HandsYJumpingStrategy(height, 5, leftHand, rightHand);
        fallingStrategy = new HandsFallingStrategy(height, radius, leftHand, rightHand);
        changeDirection(direction);
        leftHand.setRadius(radius);
        leftHand.setOffsetAngle(leftAngle);
        leftHand.setTangent(tangent);
        leftHand.setHeight(height);
        rightHand.setRadius(radius);
        rightHand.setOffsetAngle(rightAngle);
        rightHand.setTangent(tangent);
        rightHand.setHeight(height);
    }

    @Override
    public void jump(long duration) {
        jumpingStrategy.execute(duration);
    }

    @Override
    public void move(long duration) {
        walkingStrategy.execute(duration);
    }


    public void fall(long duration) {
        fallingStrategy.execute(duration);
    }

    public void attack(long durationOfAttack) {
        //TODO
    }

    public void equip(/*add weapon param model doesnt exist*/) {

    }
    //swapping states could be here
}
