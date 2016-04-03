package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observer;

/**
 * Created by Brandon on 4/1/2016.
 */
public class OffensiveRatingStat extends Stat implements Observer {
    private PrimaryStat strength, level;

    public OffensiveRatingStat(PrimaryStat strength, PrimaryStat level){
        name = "Offensive Rating";
        this.strength = strength;
        this.strength.attach(this);
        this.level = level;
        this.level.attach(this);
        update();
    }
    public void update(){
        stat = (level.getStat() + strength.getStat());
    }
}
