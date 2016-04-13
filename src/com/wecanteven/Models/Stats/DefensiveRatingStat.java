package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observer;

/**
 * Created by Brandon on 4/1/2016.
 */
public class DefensiveRatingStat extends Stat implements Observer {
    private PrimaryStat agility, level;

    public DefensiveRatingStat(PrimaryStat agility, PrimaryStat level){
        name = "Defensive Rating";
        this.agility = agility;
        this.agility.modelAttach(this);
        this.level = level;
        this.level.modelAttach(this);
        update();
    }
    public void update(){
        stat = (int)((Math.pow((.25 * level.getStat()) + 1, 3) + agility.getStat()));
    }
}
