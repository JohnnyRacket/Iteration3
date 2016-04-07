package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/7/2016.
 */
public class HandsYJumpingStrategy extends LimbStrategy {

    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;
    private final double height0;
    private final double scalingFactor;


    public HandsYJumpingStrategy(double height0, double scalingFactor, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.height0 = height0;
        this.scalingFactor = scalingFactor;
    }

    @Override
    protected void animate(double percentage) {
        leftHand.setHeight(calcPosition(percentage));
        rightHand.setHeight(calcPosition(percentage));

    }

    @Override
    protected void complete() {
        leftHand.setHeight(height0);
        rightHand.setHeight(height0);
    }

    private double calcPosition(double percentage) {
        return scalingFactor*percentage - scalingFactor*Math.pow(percentage, 2) + height0;
    }
}
