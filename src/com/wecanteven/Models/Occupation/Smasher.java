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
    protected void initSkills() {
        // General Skills
        addSkill(Skill.BIND_WOUNDS);
        addSkill(Skill.BARGAIN);
        addSkill(Skill.OBSERVATION);

        // Smasher Specific
        addSkill(Skill.BRAWLING);
        addSkill(Skill.ONE_HANDED_WEAPON);
        addSkill(Skill.TWO_HANDED_WEAPON);
    }

    @Override
    public void accept(OccupationVisitor visitor) {
        visitor.visitSmasher(this);
    }
}
