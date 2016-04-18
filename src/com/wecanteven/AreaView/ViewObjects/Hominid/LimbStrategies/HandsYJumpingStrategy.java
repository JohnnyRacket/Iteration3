package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/7/2016.
 */
public class HandsYJumpingStrategy extends LimbStrategy {

    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;
    private final double leftHeight0;
    private final double rightHeight0;
    private final double scalingFactor;


    public HandsYJumpingStrategy(double leftHeight0, double rightHeight0, double scalingFactor, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.leftHeight0 = leftHeight0;
        this.rightHeight0 = rightHeight0;
        this.scalingFactor = scalingFactor;
    }

    @Override
    protected void animate(double percentage) {
        leftHand.setHeight(calcPosition(percentage, leftHeight0));
        rightHand.setHeight(calcPosition(percentage, rightHeight0));

    }

    @Override
    protected void complete() {
        leftHand.setHeight(leftHeight0);
        rightHand.setHeight(rightHeight0);
    }

    private double calcPosition(double percentage, double height0) {
        return scalingFactor*percentage - scalingFactor*Math.pow(percentage, 2) + height0;
    }
}
