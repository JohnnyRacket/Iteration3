package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/17/2016.
 */
public class RetractingStrategy extends LimbStrategy {
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;

    private MicroData left0;
    private MicroData right0;

    private MicroData leftStart;
    private MicroData rightStart;
    private boolean hasStarted = false;

    public RetractingStrategy(MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;

        left0 = new MicroData(leftHand);
        right0 = new MicroData(rightHand);
    }

    @Override
    protected void complete() {
        left0.set(leftHand);
        right0.set(rightHand);
    }

    @Override
    protected void animate(double percentage) {
        if (!hasStarted) {
            leftStart = new MicroData(leftHand);
            rightStart = new MicroData(rightHand);
        }
        MicroData leftMid = midPoint(left0, leftStart, percentage);
        MicroData rightMid = midPoint(right0, rightStart, percentage);
        System.out.println("T: " + leftMid.t);
        leftMid.set(leftHand);
        rightMid.set(rightHand);
    }

    private MicroData midPoint(MicroData a, MicroData b, double percentage) {
        return new MicroData(
                midpoint(a.r, b.r, percentage),
                midpoint(a.t, b.t, percentage),
                midpoint(a.theta, b.theta, percentage),
                midpoint(a.h, b.h, percentage)
        );
    }

    private double midpoint(double a, double b, double percentage) {
        return (a*(percentage) + b*(1-percentage));
    }

    private class MicroData {
        public double r;
        public double t;
        public double theta;
        public double h;
        public MicroData(MicroPositionableViewObject m) {
            r = m.getRadius();
            t = m.getTangent();
            theta = m.getOffsetAngle();
            h = m.getHeight();
        }

        public MicroData(double r, double t, double theta, double h) {
            this.r = r;
            this.t = t;
            this.theta = theta;
            this.h = h;
        }

        public void set(MicroPositionableViewObject m) {
            m.setRadius(r);
            m.setTangent(t);
            m.setOffsetAngle(theta);
            m.setHeight(h);
        }
    }
}
