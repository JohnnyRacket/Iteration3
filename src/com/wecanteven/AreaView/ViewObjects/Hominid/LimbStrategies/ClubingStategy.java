package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by Alex on 4/17/2016.
 */
public class ClubingStategy extends LimbStrategy {
    private MicroPositionableViewObject leftHand;
    private MicroPositionableViewObject rightHand;
    private double theta0;
    private double leftHeight0;
    private double rightHeight0;

    public ClubingStategy(MicroPositionableViewObject rightHand, MicroPositionableViewObject leftHand) {
        this.rightHand = rightHand;
        this.leftHand = leftHand;
        this.theta0 = rightHand.getOffsetAngle();
        this.leftHeight0 = leftHand.getHeight();
        this.rightHeight0 = rightHand.getHeight();
    }

    @Override
    protected void animate(double percentage) {
        double modPercentage = Math.pow(percentage, 0.5);


        leftHand.setOffsetAngle( getAngle(modPercentage));
        rightHand.setOffsetAngle(getAngle(modPercentage));
        leftHand.setHeight( getHeight(percentage) + leftHeight0);
        rightHand.setHeight( getHeight(percentage) + rightHeight0);
    }

    @Override
    protected void complete() {
        leftHand.setHeight(0);
        rightHand.setHeight(0);

        //leftHand.setOffsetAngle(Math.PI/2);
        //rightHand.setOffsetAngle(Math.PI/2);
    }

    private double getHeight(double percentage) {
        return 5*percentage;
    }

    private double getAngle(double percentage) {
        System.out.println("********************************" + theta0*(1-percentage) + Math.PI/2*percentage);
        return theta0*(1-percentage);
    }
}
