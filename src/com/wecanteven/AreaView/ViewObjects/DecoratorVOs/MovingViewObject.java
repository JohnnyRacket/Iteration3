package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.Tiles.TileViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

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

    private JumpDetector jumpDetector;

    private TileViewObject owner;

    private Position realPosition;

    public <T extends Moveable & ViewObservable> MovingViewObject(ViewObject child, T subject, AreaView areaView, JumpDetector jumpDetector) {
        super(child);
        this.subject = subject;
        this.areaView = areaView;
        this.source = child.getPosition();
        this.destination = child.getPosition();
        this.realPosition = child.getPosition();
        this.jumpDetector = jumpDetector;

        owner = areaView.getTileViewObject(source);
        subject.attach(this);
        update();
    }


    @Override
    public Position getPosition() {
        return realPosition;
    }

    public Position calculateCurrentPosition() {
        long t = viewTime.getCurrentTime();
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
        //System.out.println("******************************** MVO updating child");
        getChild().setPosition(calculateCurrentPosition());
        if (viewTime.getCurrentTime() < endTime) {
            viewTime.register(() -> adjustPosition(endTime), 1);
        }
    }

    private void reposition() {
        if (shouldSwapNow())  {
            swap();
        } else {
            viewTime.register( this::swap, (endTime-startTime)/4*3);
        }
    }

    private void swap() {
        System.out.println("Removing from: " + owner.getPosition());
        owner.remove(this);
        System.out.println("Adding to: " + destination);

        areaView.addViewObject(this, destination);
        owner = areaView.getTileViewObject(destination);
        System.out.println("Owner is: " + owner.getPosition());
        realPosition = destination;
    }

    private boolean shouldSwapNow() {
        //TODO: account for z shit
        return (destination.getR() - source.getR()) + 2*(destination.getS() - source.getS()) > 0;
    }

    @Override
    public void update() {
        if (hasStateChange()) {
            updateState();
            System.out.println("Going from: " + source + " to " + destination);
            adjustPosition(endTime);
            reposition();
        }

    }
}
