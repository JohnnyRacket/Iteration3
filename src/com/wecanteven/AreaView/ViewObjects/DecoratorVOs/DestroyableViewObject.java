package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.FilledHex;

import java.awt.*;

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
    private boolean isDestroyed = false;

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
        destoyedViewObject.setPosition(p);
    }

    @Override
    public void update() {
        if (subject.isDestroyed() && !isDestroyed) {
            activeChild = destoyedViewObject;

            destoyedViewObject.start(duration);

            //reallyRemove(ViewTime.getInstance().getCurrentTime());
            //areaView.removeViewObject(getChild(), getPosition()); this comment is part of a refactorrrr... might need to put it back
            isDestroyed = true;
        }
        else if(!subject.isDestroyed()) {
            activeChild = getChild();
            isDestroyed = false;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        activeChild.draw(g);
    }

    private void reallyRemove(long startTime) {
        (new FilledHex(getPosition().getLocation(),2)).iterator().forEachRemaining( (location) -> {
            areaView.removeViewObject(this, location.toPosition());
        });

        if (ViewTime.getInstance().getCurrentTime() < startTime + 1000 ) {
            ViewTime.getInstance().register( () -> reallyRemove(startTime), 30);
        }
    }

}
