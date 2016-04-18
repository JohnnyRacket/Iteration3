package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/17/2016.
 */
public class SlashingStrategy extends LimbStrategy {
    private MicroPositionableViewObject leftHand;
    private double omega = 0;
    private double theta;
    private double alpha;
    private double omega0;

    public SlashingStrategy(MicroPositionableViewObject leftHand, double theta) {
        this.leftHand = leftHand;
        this.theta = theta;
        this.omega0 = leftHand.getOffsetAngle();
        this.alpha = 2*(theta - omega);
    }

    @Override
    protected void animate(double percentage) {
        leftHand.setOffsetAngle(alpha/2*Math.pow(percentage, 2) + omega*percentage + omega0);
    }

    @Override
    protected void complete() {
        leftHand.setOffsetAngle(theta + omega0);
    }
}
