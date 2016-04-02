package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class LevelStat extends Stat implements Observer,Observable {
    private PrimaryStat experience;
    private ArrayList<Observer> observers;

    public LevelStat(PrimaryStat experience){
        name = "Level";
        observers = new ArrayList<Observer>();
        this.experience = experience;
        this.experience.attach(this);
        update();
    }
    public void update(){
        stat = (experience.getStat())/100;
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void levelUp(){
        stat++;
        notifyObservers();
    }

}
