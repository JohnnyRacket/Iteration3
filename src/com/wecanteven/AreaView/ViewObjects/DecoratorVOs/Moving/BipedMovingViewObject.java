package com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;

/**
 * Created by Alex on 3/31/2016.
 */
public class BipedMovingViewObject extends MovingViewObject implements Observer {
    private JumpDetector jumpDetector;
    public <T extends Moveable & ViewObservable> BipedMovingViewObject(ViewObject child, T subject, AreaView areaView, JumpDetector jumpDetector) {
        super(child, subject, areaView);
        this.jumpDetector = jumpDetector;
    }

    public Position calculateCurrentPosition(Position source, Position destination, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTime();
        if (t >= endTime) return destination;

        double percentage = (double)(t - startTime)/(double)(endTime - startTime);


        return new Position(
                inBetween(source.getR(), destination.getR(), percentage),
                inBetween(source.getS(), destination.getS(), percentage),
                parabola(source, destination, percentage)
        );
    }



    private double inBetween(double start, double end, double percentage) {
        return start + percentage*(end - start);
    }

    private double parabola(Position startLoc, Position endLoc, double percentage) {
        double start = startLoc.getZ();
        double end = endLoc.getZ();
        double deltaY = end - start;

        if (jumpDetector.isJumping(startLoc, endLoc)) {
            double jumpConstant = 0.8;
            double a = -2*deltaY -4*jumpConstant;
            double v = 3*deltaY+4*jumpConstant;
            return a*Math.pow(percentage, 2) + v*percentage + start;
        } else {
            return start + (end - start) * percentage;
        }



    }

}
