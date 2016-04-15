package com.wecanteven.Observers;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.ModelTime.ModelTime;

import java.util.ArrayList;

/**
 * Created by Alex on 3/31/2016.
 */
public interface ViewObservable {
    ArrayList<Observer> getObservers();
    default void attach(Observer o) {
        ModelTime.getInstance().registerAlertable(() -> getObservers().add(o), 1);
    }
    default void dettach(Observer o) {
        ModelTime.getInstance().registerAlertable(() -> getObservers().remove(o), 1);
    }
    default void notifyObservers() {
        ViewTime.getInstance().register( () -> getObservers().forEach( Observer::update ), 1 );
    }
}
