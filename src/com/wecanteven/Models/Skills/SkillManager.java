package com.wecanteven.Models.Skills;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Visitors.SkillVisitor;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by simonnea on 4/7/16.
 */
public abstract class SkillManager {
    public SkillManager() {
        initStorage();

        addSkill(new Skill("Bind Wounds"));
        addSkill(new Skill("Bargain"));
        addSkill(new Skill("Observation"));
    }

    public SkillManager(Collection<Skill> skills) {
        initStorage();

        for (Skill s : skills) {
            addSkill(s);
        }
    }

    public void addSkillPoint(Skill skill) {
        getSkill(skill).incrementSkillPoints();
    }

    public abstract Skill getSkill(Skill skill);

    public abstract void configureAbility(Ability ability);

    protected abstract void addSkill(Skill skill);

    protected abstract void initStorage();

    public abstract Iterator<Skill> getIterator();

    public void accept(SkillVisitor visitor) {
        visitor.visitSkillManager(this);
    }
}
