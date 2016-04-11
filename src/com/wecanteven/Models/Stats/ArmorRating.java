package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observer;

/**
 * Created by Brandon on 4/1/2016.
 */
public class ArmorRating extends Stat implements Observer {
    private PrimaryStat hardiness;

    public ArmorRating(PrimaryStat hardiness){
        name = "Armor Rating";
        this.hardiness = hardiness;
        this.hardiness.modelAttach(this);
        update();
    }
    public void update(){
        stat = (hardiness.getStat());
    }
}
