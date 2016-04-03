package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/1/2016.
 */
public class LevelStat extends PrimaryStat implements Observer,ViewObservable {
    private int level;              //used to check to see if the level changed after you gained xp
    private PrimaryStat experience;

    public LevelStat(PrimaryStat experience){
        super("Level",1);
        level = 1;
        observers = new ArrayList<>();
        this.experience = experience;
        this.experience.attach(this);
        update();
    }
    public void update(){
        stat = level + (experience.getStat())/100;
        boolean levelHasChanged = false;            //used if you gain more than 1, level so you don't have to continually
        while(level < stat){                            //update the stats, just only after it finishes leveling.
            level++;
            experience.add(-100);
            levelHasChanged = true;
        }
        if(levelHasChanged){
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
