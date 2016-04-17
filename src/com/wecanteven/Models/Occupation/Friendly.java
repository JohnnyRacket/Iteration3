package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.OccupationVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.TerranianCanMoveVisitor;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class Friendly extends Occupation {
    public Friendly(){
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,1,1,1,1,0,0,0,0);
    }

    @Override
    protected void initSkills() {

    }

    @Override
    public void accept(OccupationVisitor visitor) {
        visitor.visitFriendly(this);
    }
}
