package com.wecanteven.Models.Skills;

import java.util.Collection;

/**
 * Created by simonnea on 4/8/16.
 */
public class SmasherSkillManager extends HashMapSkillManager {

    public SmasherSkillManager() {
        super();

        addSkill(new Skill("One-handed Attack"));
        addSkill(new Skill("Two-handed Attack"));
        addSkill(new Skill("Brawler Attack"));
    }

    public SmasherSkillManager(Collection<Skill> skills) {
        super(skills);
    }
}
