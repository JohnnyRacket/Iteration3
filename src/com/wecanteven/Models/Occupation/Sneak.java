package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public class Sneak extends Occupation {
    public Sneak(){
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,1,5,1,1,0,0,0,0);
    }
}
