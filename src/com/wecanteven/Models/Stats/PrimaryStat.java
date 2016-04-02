package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class PrimaryStat extends Stat implements Observable {
    private ArrayList<Observer> observers;
    public PrimaryStat(int stat){
        observers = new ArrayList<Observer>();
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
}
