package com.wecanteven.Observers;

import com.wecanteven.AreaView.ViewTime;

import java.util.ArrayList;

/**
 * Created by Alex on 3/31/2016.
 */
public interface Observable {
    ArrayList<Observer> getObservers();
    default void attach(Observer o) {
        getObservers().add(o);
    }
    default void dettach(Observer o) {
        getObservers().remove(o);
    }
    default void notifyObservers() {
        ViewTime.getInstance().register( () -> getObservers().forEach( Observer::update ), 1 );
    }
}
