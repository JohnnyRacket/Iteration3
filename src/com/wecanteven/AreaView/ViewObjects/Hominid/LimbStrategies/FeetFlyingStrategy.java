package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/15/2016.
 */
public class FeetFlyingStrategy extends LimbStrategy {
    private double retractedHeight;
    private MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;
    public FeetFlyingStrategy(double height, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.retractedHeight = height;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
    }

    @Override
    protected void animate(double percentage) {
        leftFoot.setHeight(retractedHeight);
        rightFoot.setHeight(retractedHeight);
    }

    @Override
    protected void complete() {
        leftFoot.setHeight(retractedHeight);
        rightFoot.setHeight(retractedHeight);
    }
}
