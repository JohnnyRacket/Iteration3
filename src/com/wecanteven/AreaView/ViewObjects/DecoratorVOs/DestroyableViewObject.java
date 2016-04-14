package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;

import java.awt.*;
import java.awt.geom.Area;

/**
 * Created by Alex on 4/7/2016.
 */
public class DestroyableViewObject extends DecoratorViewObject implements Observer {
    private Destroyable subject;
    private StartableViewObject destoyedViewObject;
    private ViewObject child;
    private ViewObject activeChild;
    private long duration;
    private AreaView areaView;

    public <T extends Destroyable & ViewObservable> DestroyableViewObject(ViewObject child, StartableViewObject destoyedViewObject, T subject, AreaView areaView, long duration) {
        super(child);
        this.child = child;
        this.destoyedViewObject = destoyedViewObject;
        this.subject = subject;
        this.areaView = areaView;
        this.activeChild = this.child;
        this.duration = duration;
        subject.attach(this);
    }

    @Override
    public void setPosition(Position p) {
        super.setPosition(p);
        //destoyedViewObject.setPosition(p);
    }

    @Override
    public void update() {
        if (subject.isDestroyed()) {
            destoyedViewObject.setPosition(getPosition());
            areaView.addViewObject(destoyedViewObject, getPosition());
            destoyedViewObject.start(duration);
        }
    }

}
