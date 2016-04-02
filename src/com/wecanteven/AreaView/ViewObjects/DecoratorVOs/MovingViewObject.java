package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class MovingViewObject extends DecoratorViewObject implements Observer {
    private AreaView areaView;

    private Position source;
    private Position destination;

    private long startTime = 0;
    private long endTime = 0;

    private ViewTime viewTime = ViewTime.getInstance();

    private Moveable subject;

    public MovingViewObject(ViewObject child, Moveable subject, AreaView areaView) {
        super(child);
        this.subject = subject;
        this.areaView = areaView;
        this.source = child.getPosition();
        this.destination = child.getPosition();
    }


    public Position calculateCurrentPosition() {
        long t = viewTime.getCurrentTime();
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

    private boolean hasStateChange() {
        return !subject.getLocation().toPosition().equals(destination);
    }
    private void updateState() {
        source = calculateCurrentPosition();
        destination = subject.getLocation().toPosition();
        startTime = ViewTime.getInstance().getCurrentTime();
        endTime = startTime + subject.getMovingTicks()* Config.MODEL_TICK;
    }


    private void adjustPosition(long endTime) {
        getChild().setPosition(calculateCurrentPosition());
        if (viewTime.getCurrentTime() < endTime) {
            viewTime.register(() -> adjustPosition(endTime), 1);
        }
    }

    private void reposition() {
        if (shouldSwapNow())  {
            swap();
        } else {
            viewTime.register( this::swap, endTime-startTime);
        }
    }

    private void swap() {
        areaView.removeViewObject(this, source);
        areaView.addViewObject(this, destination);
    }

    private boolean shouldSwapNow() {
        return (destination.getR() - source.getR()) + 2*(destination.getS() - source.getS()) > 0;
    }

    @Override
    public void update() {
        if (hasStateChange()) {
            updateState();
            //TODO: make sure multiple threads of this cant start
            adjustPosition(endTime);
            reposition();
        }

    }
}
