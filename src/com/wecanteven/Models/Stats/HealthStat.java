package com.wecanteven.Models.Stats;

import com.wecanteven.Observers.Observer;

/**
 * Created by Brandon on 4/1/2016.
 */
public class HealthStat extends Stat implements Observer {
    private PrimaryStat hardiness,level;

    public HealthStat(PrimaryStat hardiness, PrimaryStat level){
        name = "Health";
        this.hardiness = hardiness;
        this.hardiness.modelAttach(this);
        this.level = level;
        this.level.modelAttach(this);
        update();
    }
    public void update(){
        stat = (level.getStat() + hardiness.getStat());
    }
}
