package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.*;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by adamfortier on 4/11/16.
 */
public class OneHandedWeaponState extends HandState {

    private final double tangent = 0d;
    private final double radius = 0.5d;
    private final double leftAngle = Math.PI/2;
    private final double rightAngle = -leftAngle;
    private final double height = 2d;

    private LimbStrategy walkingStrategy;
    private LimbStrategy jumpingStrategy;
    private LimbStrategy fallingStrategy;

    private LimbStrategy slashingStrategy;
    private RetractingStrategy retractingStrategy;

    public OneHandedWeaponState(Direction direction, MicroPositionableViewObject rightHand, MicroPositionableViewObject leftHand) {
        super(leftHand, rightHand);
        changeDirection(direction);
        leftHand.setRadius(radius);
        leftHand.setOffsetAngle(leftAngle);
        leftHand.setTangent(tangent);
        leftHand.setHeight(height);
        rightHand.setRadius(radius);
        rightHand.setOffsetAngle(rightAngle);
        rightHand.setTangent(tangent);
        rightHand.setHeight(height);
        walkingStrategy = new HandWalkingStrategy(0.3, leftHand, rightHand);
        jumpingStrategy = new HandsYJumpingStrategy(height, height, 5, leftHand, rightHand);
        fallingStrategy = new HandsFallingStrategy(height, height, radius, leftHand, rightHand);
        slashingStrategy = new SlashingStrategy(rightHand, Math.PI*5/6);
        retractingStrategy = new RetractingStrategy(leftHand, rightHand);
    }

    @Override
    public void jump(long duration) {
        jumpingStrategy.execute(duration);
    }

    @Override
    public void move(long duration) {
        walkingStrategy.execute(duration);
    }


    public void setLocation(Location location) {
        //TODO
    }

    public void fall(long duration) {
        fallingStrategy.execute(duration);
    }

    public Location getLocation() {
        return null;
    }

    public void attack(long windUp, long coolDown) {
        slashingStrategy.execute(windUp);
        ViewTime.getInstance().register(() -> retractingStrategy.execute(coolDown), windUp);
    }

    public void equip(/*add weapon param model doesnt exist*/) {

    }
    //swapping states could be here
}