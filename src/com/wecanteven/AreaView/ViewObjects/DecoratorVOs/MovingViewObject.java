package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class MovingViewObject extends DecoratorViewObject {
    private AreaView areaView;

    private Position source;
    private Position destination;

    private long startTime = 0;
    private long endTime = 0;

    public MovingViewObject(ViewObject child, AreaView areaView) {
        super(child);
        this.areaView = areaView;
        this.source = child.getPosition();
        this.destination = child.getPosition();
    }


    public Position calculateCurrentPosition() {
        long t = ViewTime.getInstance().getCurrentTime();
        if (t >= endTime) return destination;

        double percentage = (t - startTime)/(endTime - startTime);

        return new Position(
                inBetween(source.getR(), source.getR(), percentage),
                inBetween(source.getS(), source.getS(), percentage),
                inBetween(source.getZ(), source.getZ(), percentage)
        );
    }

    private double inBetween(double start, double end, double percentage) {
        return percentage*end + (1 - percentage)*start;
    }

    @Override
    public void draw(Graphics2D g) {
        getChild().setPosition(calculateCurrentPosition());
        super.draw(g);
    }
}
