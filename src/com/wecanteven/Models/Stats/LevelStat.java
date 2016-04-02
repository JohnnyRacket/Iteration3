package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class LevelStat extends Stat implements Observer,Observable {
    private int level;
    private PrimaryStat experience;
    private ArrayList<Observer> observers;

    public LevelStat(PrimaryStat experience){
        name = "Level";
        level = 1;
        observers = new ArrayList<>();
        this.experience = experience;
        this.experience.attach(this);
        update();
    }
    public void update(){
        stat = (experience.getStat())/100 + 1;
        while(level < stat){
            level++;
            notifyObservers();
        }
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    @Override
    public void notifyObservers(){
        for(Observer stat: observers){
            stat.update();
        }
    }
}
