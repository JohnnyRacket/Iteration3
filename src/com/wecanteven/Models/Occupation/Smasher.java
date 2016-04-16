package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.OccupationVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Smasher extends Occupation{
    public Smasher(){
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,5,1,1,1,0,0,0,0);
    }
    @Override
    public void accept(OccupationVisitor visitor) {
        visitor.visitSmasher(this);
    }
}
