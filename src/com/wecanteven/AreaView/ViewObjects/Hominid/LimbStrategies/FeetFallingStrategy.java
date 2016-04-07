package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class FeetFallingStrategy extends LimbStrategy {
    private double maxTangent;
    private MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;

    public FeetFallingStrategy(double maxTangent, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.maxTangent = maxTangent;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
    }

    @Override
    public void complete() {
        leftFoot.setTangent(0);
        rightFoot.setTangent(0);
    }

    @Override
    public void animate(double percentage) {
        leftFoot.setTangent(-maxTangent*Math.sin(percentage*2*Math.PI));
        rightFoot.setTangent(-maxTangent*Math.sin(percentage*2*Math.PI));
    }
}
