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
        this.strength.modelAttach(this);
        this.level = level;
        this.level.modelAttach(this);
        update();
    }
    public void update(){
        stat = (int)((Math.pow((.25 * level.getStat()) + 1, 3) + strength.getStat()));
    }
}
