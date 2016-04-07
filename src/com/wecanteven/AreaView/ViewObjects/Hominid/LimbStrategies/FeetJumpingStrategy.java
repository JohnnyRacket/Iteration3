package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class FeetJumpingStrategy extends LimbStrategy {

    private MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;
    private final double initalHeight;
    private final double scalingFactor;


    public FeetJumpingStrategy(double initialHeight, double scalingFactor, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        this.initalHeight = initialHeight;
        this.scalingFactor = scalingFactor;
    }

    @Override
    protected void animate(double percentage) {
        leftFoot.setHeight(calcPosition(percentage));
        rightFoot.setHeight(calcPosition(percentage));

    }

    @Override
    protected void complete() {
        leftFoot.setHeight(initalHeight);
        rightFoot.setHeight(initalHeight);
    }

    private double calcPosition(double percentage) {
        return scalingFactor*percentage - scalingFactor*Math.pow(percentage, 2) + initalHeight;
    }
}
