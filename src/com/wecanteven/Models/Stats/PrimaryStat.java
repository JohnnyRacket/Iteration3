package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.ModelObservable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class PrimaryStat extends Stat implements ModelObservable{
    protected ArrayList<Observer> observers;

    public PrimaryStat(String name,int stat){
        this.name = name;
        observers = new ArrayList<>();
        this.stat = stat;
    }
    @Override
    public ArrayList<Observer> getModelObservers() {
        return observers;
    }

    public void add(int addable){
        if(addable == 0) return;
        stat += addable;
        modelNotifyObservers();
    }

    public void subtract(int subtractable){
        stat -= subtractable;
        modelNotifyObservers();

    }

    @Override
    public void modelNotifyObservers(){
        for(Observer stat: observers){
            stat.update();
        }
    }
}
