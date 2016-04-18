package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class HandsFallingStrategy extends LimbStrategy {
    private double leftHeight0;
    private double rightHeight0;
    private double radius0;
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;

    public HandsFallingStrategy(double leftHeight0, double rightHeight0, double radius0, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHeight0 = leftHeight0;
        this.rightHeight0 = rightHeight0;
        this.radius0 = radius0;
        this.leftHand = leftHand;
        this.rightHand = rightHand;


    }

    @Override
    public void complete() {
        leftHand.setHeight(leftHeight0);
        leftHand.setRadius(radius0);
        rightHand.setHeight(rightHeight0);
        rightHand.setRadius(radius0);
    }

    @Override
    public void animate(double percentage) {
        leftHand.setRadius(radius0 + radius0*fatSine(percentage)/4);
        leftHand.setHeight(leftHeight0 + leftHeight0 *fatSine(percentage)/4);
        rightHand.setRadius(radius0 + radius0*fatSine(percentage)/4);
        rightHand.setHeight(rightHeight0 + rightHeight0 *fatSine(percentage)/4);
    }

    private double fatSine(double percentage) {
        return Math.pow(Math.sin(Math.PI*percentage), 0.5);
    }
}
