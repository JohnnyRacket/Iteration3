package com.wecanteven.Visitors;

import com.wecanteven.Models.Stats.Stats;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public interface StatsVisitor {
    public void visitStats(Stats stats);


}
