package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observer;

/**
 * Created by Alex on 4/7/2016.
 */
public class DestroyableViewObject extends DecoratorViewObject implements Observer {
    private Destroyable subject;
    private StartableViewObject child;
    public DestroyableViewObject(StartableViewObject child, Destroyable subject) {
        super(child);
        this.child = child;
        this.subject = subject;
    }

    @Override
    public void update() {
        if (subject.isDestroyed()) {
            child.start(1000);
        }

    }
}
