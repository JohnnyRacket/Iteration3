package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.OccupationVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.TerranianCanMoveVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public class Enemy extends Occupation {
    public Enemy(){
        super();
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,1,1,1,1,0,0,0,0);
    }

    @Override
    protected void initSkills() {
        // TODO maybe add some stuff for AI targeting
    }

    @Override
    public void accept(OccupationVisitor visitor) {

        visitor.visitEnemy(this);
    }
}
