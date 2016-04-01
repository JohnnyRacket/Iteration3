package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;

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

    private Moveable subject;

    public MovingViewObject(ViewObject child, Moveable subject, AreaView areaView) {
        super(child);
        this.subject = subject;
        this.areaView = areaView;
        this.source = child.getPosition();
        this.destination = child.getPosition();
    }


    public Position calculateCurrentPosition() {
        long t = ViewTime.getInstance().getCurrentTime();
        if (t >= endTime) return destination;

        double percentage = (double)(t - startTime)/(double)(endTime - startTime);
        System.out.println("t - startTime: " + (t - startTime));
        System.out.println("% : " +  (double)(t - startTime)/(double)(endTime - startTime));
        System.out.println("r: " + inBetween(source.getR(), source.getR(), percentage));


        return new Position(
                inBetween(source.getR(), destination.getR(), percentage),
                inBetween(source.getS(), destination.getS(), percentage),
                inBetween(source.getZ(), destination.getZ(), percentage)
        );
    }

    private double inBetween(double start, double end, double percentage) {
        System.out.println("end: " + percentage*end );
        System.out.println("start: " + (1d - percentage)*start );
        return start + percentage*(end - start);
    }

    @Override
    public void draw(Graphics2D g) {
        getChild().setPosition(calculateCurrentPosition());
        super.draw(g);
    }

    public boolean hasStateChange() {
        return !subject.getLocation().toPosition().equals(destination);
    }
    public void updateState() {
        source = calculateCurrentPosition();
        destination = subject.getLocation().toPosition();
        startTime = ViewTime.getInstance().getCurrentTime();
        endTime = startTime + subject.getMovingTicks()* Config.MODEL_TICK;
    }

    @Override
    public void update() {
        if (hasStateChange()) updateState();
    }
}
