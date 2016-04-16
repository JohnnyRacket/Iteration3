package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.OccupationVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public class Sneak extends Occupation {
    public Sneak(){
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,1,5,1,1,0,0,0,0);
    }

    @Override
    protected void initSkills() {
        // General Skills
        addSkill(Skill.BIND_WOUNDS);
        addSkill(Skill.BARGAIN);
        addSkill(Skill.OBSERVATION);

        // Sneak Skill
        addSkill(Skill.CREEP);
        addSkill(Skill.DETECT_AND_REMOVE);
        addSkill(Skill.PICK_POCKET);
        addSkill(Skill.RANGED_WEAPON);
    }

    @Override
    public void accept(OccupationVisitor visitor) {
        visitor.visitSneak(this);
    }
}
