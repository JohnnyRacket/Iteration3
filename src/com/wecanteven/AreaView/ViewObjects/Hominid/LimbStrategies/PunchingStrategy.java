package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;

/**
 * Created by Alex on 4/16/2016.
 */
public class PunchingStrategy extends LimbStrategy {
    private double distance;
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;
    private StartableViewObject leftWeapon;
    private StartableViewObject rightWeapon;

    private MicroPositionableViewObject currentHand;
    private StartableViewObject currentWeapon;

    private boolean hasStarted = false;

    private final double v = -1d;
    private final double a;
    private int multiplier = 1;
    public PunchingStrategy(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand, double distance) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.distance = distance;
        this.multiplier = -1;
        this.currentHand = rightHand;

        this.a = 2*(distance - v);


    }

    @Override
    protected void animate(double percentage) {
        currentHand.setTangent(multiplier*(a/2*Math.pow(percentage, 2) + v*percentage));
    }

    @Override
    protected void complete() {
        currentHand.setTangent(distance);

        if (currentHand == leftHand) {
            multiplier = -1;
            currentHand = rightHand;
        }
        else {
            multiplier = 1;
            currentHand = leftHand;
        }
    }
}
