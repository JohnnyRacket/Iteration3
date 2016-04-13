package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class FeetWalkingStrategy extends LimbStrategy {
    private double maxTangent;
    private MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;

    public FeetWalkingStrategy(double maxTangent, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.maxTangent = maxTangent;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
    }

    @Override
    public void complete() {
        leftFoot.setTangent(0);
        rightFoot.setTangent(0);
        leftFoot.setHeight(0);
        rightFoot.setHeight(0);
    }

    @Override
    public void animate(double percentage) {
        double tangent = -maxTangent*Math.sin(percentage*2*Math.PI);

        leftFoot.setTangent(tangent);
        rightFoot.setTangent(tangent);

        double height = Math.abs(Math.sin(percentage*2*Math.PI))/10;

        leftFoot.setHeight(tangent < 0 ? height : 0);
        rightFoot.setHeight(tangent > 0 ? height : 0);
    }
}
