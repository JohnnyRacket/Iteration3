package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/6/2016.
 */
public class HandWalkingStrategy extends LimbStrategy {

    private double maxTangent;
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;

    public HandWalkingStrategy(double maxTangent, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.maxTangent = maxTangent;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public void complete() {
        leftHand.setTangent(0);
        rightHand.setTangent(0);
    }

    @Override
    public void animate(double percentage) {
        leftHand.setTangent(maxTangent*Math.sin(percentage*2*Math.PI));
        rightHand.setTangent(maxTangent*Math.sin(percentage*2*Math.PI));
    }
}
