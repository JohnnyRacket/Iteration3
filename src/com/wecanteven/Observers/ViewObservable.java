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
        ArrayList<Observer> observers = new ArrayList<>();
//        getObservers().forEach( (observer) -> observers.add(observer) );
    try {
        for (int i = 0; i < getObservers().size(); i++) {
            observers.add(getObservers().get(i));
        }
//        ViewTime.getInstance().register( () -> observers.forEach( Observer::update ), 1 );

        ViewTime.getInstance().register(
                () -> {
                    for (int i = 0; i < observers.size(); i++)
                        observers.get(i).update();
                }, 1);
    }
    catch (Exception e) {
        System.out.println("Concurrent Mod in ViewObservable");
    }


    }
}
