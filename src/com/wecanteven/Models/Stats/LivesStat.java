package com.wecanteven.Models.Stats;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/2/2016.
 */
public class LivesStat extends PrimaryStat implements Observer {
    private PrimaryStat health;
    private Entity ownerOfLives;
    public LivesStat(PrimaryStat health,Entity entity){
        super("Lives",3);
        observers = new ArrayList<>();
        this.health = health;
        health.modelAttach(this);
        modelAttach(entity);
        update();
    }
    @Override
    public void add(int addable){
        if(addable == 0) return;
        stat += addable;
    }
    public void update(){

        if(health.getStat() <= 0){

            stat--;
            modelNotifyObservers();
        }
    }
}