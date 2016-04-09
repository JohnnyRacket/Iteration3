package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class HandsFallingStrategy extends LimbStrategy {
    private double height0;
    private double radius0;
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;

    public HandsFallingStrategy(double height0, double radius0, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.height0 = height0;
        this.radius0 = radius0;
        this.leftHand = leftHand;
        this.rightHand = rightHand;


    }

    @Override
    public void complete() {
        leftHand.setHeight(height0);
        leftHand.setRadius(radius0);
        rightHand.setHeight(height0);
        rightHand.setRadius(radius0);
    }

    @Override
    public void animate(double percentage) {
        leftHand.setRadius(radius0 + radius0*fatSine(percentage)/2);
        leftHand.setHeight(height0 + height0*fatSine(percentage));
        rightHand.setRadius(radius0 + radius0*fatSine(percentage)/2);
        rightHand.setHeight(height0 + height0*fatSine(percentage));
    }

    private double fatSine(double percentage) {
        return Math.pow(Math.sin(Math.PI*percentage), 0.5);
    }
}
