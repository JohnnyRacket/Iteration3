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
        this.hardiness.attach(this);
        this.level = level;
        this.level.attach(this);
        update();
    }
    public void update(){
        stat = (level.getStat() + hardiness.getStat());
    }
}
