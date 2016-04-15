package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.*;
import com.wecanteven.UtilityClasses.HexRing;

/**
 * Created by alexs on 4/10/2016.
 */
public class VisibilitySourceViewObject extends DecoratorViewObject implements Observer {
    private AreaView area;
    private int radius;
    private Position position;
    private Positionable subject;

    public <T extends Positionable & ViewObservable> VisibilitySourceViewObject(ViewObject child, T subject, AreaView area, int radius) {
        super(child);
        this.area = area;
        this.radius = radius;
        this.subject = subject;

        this.position = subject.getLocation().toPosition();

        subject.attach(this);
        ViewTime.getInstance().register( () -> reveal(radius), 1000);
    }

    private void conceal(int radius) {
        for (int i = 0; i < radius; i++) {
            HexRing darkRing = new HexRing(i + 1, position.getLocation());
            darkRing.iterator().forEachRemaining( (location ->  area.conceal(location.toPosition())));
        }

    }
    private void reveal(int radius) {
        for (int i = 0; i<= radius; i++) {
            HexRing visibleRing = new HexRing(i, position.getLocation());
            visibleRing.iterator().forEachRemaining( (location ->  area.reveal(location.toPosition())));
        }
    }

    @Override
    public void update() {
        conceal(radius);
        this.position = subject.getLocation().toPosition();
        reveal(radius);
    }
}
