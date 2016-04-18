package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;

/**
 * Created by adamfortier on 4/17/16.
 */
public class MountMovement extends LimbStrategy {
    private MicroPositionableViewObject mount;
    private double maxTangent;


    public MountMovement(MicroPositionableViewObject mount, double maxTangent) {
        this.mount = mount;
        this.maxTangent = maxTangent;
    }

    @Override
    public void complete() {
        mount.setTangent(0);
        mount.setHeight(0);
    }

    @Override
    public void animate(double percentage) {
        double tangent = -maxTangent*Math.sin(percentage*2*Math.PI);

        mount.setTangent(tangent);

        double height = Math.abs(Math.sin(percentage*2*Math.PI))/10;

        mount.setHeight(tangent < 0 ? height : 0);}
}
