package com.wecanteven.AreaView.ViewObjects.DecoratorVOs.Moving;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.Tiles.TileViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Config;

/**
 * Created by Alex on 4/15/2016.
 */
public abstract class MovingViewObject extends DecoratorViewObject implements Observer{
    private AreaView areaView;

    private Position source;
    private Position destination;

    private long startTime = 0;
    private long endTime = 0;

    private ViewTime viewTime = ViewTime.getInstance();

    private Moveable subject;


    private TileViewObject owner;

    private Position realPosition;

    public <T extends Moveable & ViewObservable> MovingViewObject(ViewObject child, T subject, AreaView areaView) {
        super(child);
        this.subject = subject;
        this.areaView = areaView;
        this.source = child.getPosition();
        this.destination = child.getPosition();
        this.realPosition = child.getPosition();

        owner = areaView.getTileViewObject(source);
        subject.attach(this);
        update();
    }


    @Override
    public Position getPosition() {
        return realPosition;
    }

    protected abstract Position calculateCurrentPosition(Position source, Position destination, long startTime, long endTime);


    private boolean hasStateChange() {
        return !subject.getLocation().toPosition().equals(destination);
    }
    private void updateState() {
        source = calculateCurrentPosition(source, destination, startTime, endTime);
        destination = subject.getLocation().toPosition();
        startTime = ViewTime.getInstance().getCurrentTime();
        endTime = startTime + subject.getMovingTicks()* Config.MODEL_TICK;
    }


    private void adjustPosition(long endTime) {
        //
        getChild().setPosition(calculateCurrentPosition(source, destination, startTime, this.endTime));
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

        owner.remove(this);


        areaView.addViewObject(this, destination);
        owner = areaView.getTileViewObject(destination);

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

            adjustPosition(endTime);
            reposition();
        }

    }
}
