package com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.ViewObservable;

/**
 * Created by Alex on 4/15/2016.
 */
public class SimpleMovingViewObject extends MovingViewObject {
    public <T extends Moveable & ViewObservable> SimpleMovingViewObject(ViewObject child, T subject, AreaView areaView) {
        super(child, subject, areaView);
    }

    @Override
    protected Position calculateCurrentPosition(Position source, Position destination, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTime();
        if (t >= endTime) return destination;

        double percentage = (double)(t - startTime)/(double)(endTime - startTime);


        return new Position(
                inBetween(source.getR(), destination.getR(), percentage),
                inBetween(source.getS(), destination.getS(), percentage),
                inBetween(source.getZ(), destination.getZ(), percentage)
        );
    }

    private double inBetween(double start, double end, double percentage) {
        return start + percentage*(end - start);
    }

}
