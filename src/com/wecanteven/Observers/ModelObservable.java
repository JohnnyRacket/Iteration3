package com.wecanteven.Observers;

import java.util.ArrayList;

/**
 * Created by Alex on 3/31/2016.
 */
public interface ModelObservable {
    ArrayList<Observer> getModelObservers();
    default void modelAttach(Observer o) {
        getModelObservers().add(o);
    }
    default void modelDettach(Observer o) {
        getModelObservers().remove(o);
    }
    default void modelNotifyObservers() {
       try {
           for (int i = 0; i < getModelObservers().size(); i++) {
               getModelObservers().get(i).update();
           }
       }
       catch (IndexOutOfBoundsException e) {
           System.out.println("Current Mod in  ModelObservable");
       }
    }
}
