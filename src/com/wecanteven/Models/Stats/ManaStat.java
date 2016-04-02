package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observer;

/**
 * Created by Brandon on 4/1/2016.
 */
public class ManaStat extends Stat implements Observer{
    private PrimaryStat intellect, level;

    public ManaStat(PrimaryStat intellect, PrimaryStat level){
        name = "Mana";
        this.intellect = intellect;
        this.intellect.attach(this);
        this.level = level;
        this.level.attach(this);
        update();
    }
    public void update(){
        stat = (level.getStat() + intellect.getStat());
    }
}
