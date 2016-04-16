package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.CanMoveVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class Occupation implements OccupationVisitable{
    protected StatsAddable statsAddable;
    private CanMoveVisitor canMoveVisitor;

    public StatsAddable getStatsAddable() {
        return statsAddable;
    }

    public void setStatsAddable(StatsAddable statsAddable) {
        this.statsAddable = statsAddable;
    }

    public CanMoveVisitor getCanMoveVisitor() {
        return canMoveVisitor;
    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        this.canMoveVisitor = canMoveVisitor;
    }

}
