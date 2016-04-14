package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observer;

import java.awt.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class DestroyableViewObject extends DecoratorViewObject implements Observer {
    private Destroyable subject;
    private StartableViewObject destoyedViewObject;
    private ViewObject child;
    private ViewObject activeChild;
    public DestroyableViewObject(ViewObject child, StartableViewObject destoyedViewObject, Destroyable subject) {
        super(child);
        this.child = child;
        this.destoyedViewObject = destoyedViewObject;
        this.subject = subject;
        this.activeChild = this.child;
    }

    @Override
    public void setPosition(Position p) {
        super.setPosition(p);
        destoyedViewObject.setPosition(p);
    }

    @Override
    public void update() {
        if (subject.isDestroyed()) {
            destoyedViewObject.start(1000);
            activeChild = destoyedViewObject;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        activeChild.draw(g);
    }
}
