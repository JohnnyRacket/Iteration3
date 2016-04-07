package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/7/16.
 */
public class FeetFallingStrategy extends LimbStrategy {
    private double maxTangent;
    private double initialHeight;
    private double scalingFactor;
    private MicroPositionableViewObject leftFoot;
    private MicroPositionableViewObject rightFoot;

    public FeetFallingStrategy(double maxTangent, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
        this.maxTangent = maxTangent;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
    }



//    public FeetFallingStrategy(double initialHieght, double scalingFactor, MicroPositionableViewObject leftFoot, MicroPositionableViewObject rightFoot) {
//        this.leftFoot = leftFoot;
//        this.rightFoot = rightFoot;
//        this.initialHeight = initialHieght;
//        this.scalingFactor = scalingFactor;
//    }

    @Override
    public void complete() {
        leftFoot.setTangent(0);
        rightFoot.setTangent(0);
//        leftFoot.setHeight(initialHeight);
//        rightFoot.setHeight(initialHeight);
    }

    @Override
    public void animate(double percentage) {
        System.out.println("FALLING ANIMATE");
        leftFoot.setTangent(-maxTangent*Math.sin(percentage*2*Math.PI));
        rightFoot.setTangent(-maxTangent*Math.sin(percentage*2*Math.PI));
//        leftFoot.setHeight(calcPosition(percentage));
//        rightFoot.setHeight(calcPosition(percentage));
    }


    private double calcPosition(double percentage) {
        return scalingFactor*percentage - scalingFactor*Math.pow(percentage, 2) + initialHeight;
    }
}
