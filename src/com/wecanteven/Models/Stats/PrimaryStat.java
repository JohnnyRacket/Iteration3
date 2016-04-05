package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class PrimaryStat extends Stat implements ViewObservable {
    protected ArrayList<Observer> observers;

    public PrimaryStat(String name,int stat){
        this.name = name;
        observers = new ArrayList<>();
        this.stat = stat;
    }
    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void add(int addable){
        stat += addable;
        notifyObservers();
    }

    public void subtract(int subtractable){
        stat += subtractable;
        notifyObservers();
    }

    @Override
    public void notifyObservers(){
        for(Observer stat: observers){
            stat.update();
        }
    }
}
