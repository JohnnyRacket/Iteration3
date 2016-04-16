package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.OccupationVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public class Summoner extends Occupation {
    public Summoner(){
        super();
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        statsAddable = new StatsAddable(0,1,1,5,1,0,0,0,0);
    }

    @Override
    protected void initSkills() {
        // General Skills
        addSkill(Skill.BIND_WOUNDS);
        addSkill(Skill.BARGAIN);
        addSkill(Skill.OBSERVATION);

        // Summoner Skills
        addSkill(Skill.ENCHANTMENT);
        addSkill(Skill.BANE);
        addSkill(Skill.BOON);
        addSkill(Skill.STAFF);
    }

    @Override
    public void accept(OccupationVisitor visitor) {
        visitor.visitSummoner(this);
    }
}
