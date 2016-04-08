package com.wecanteven.Models.Skills;

import com.wecanteven.Models.Abilities.Ability;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by simonnea on 4/7/16.
 */
public class SneakSkillManager extends HashMapSkillManager {

    public SneakSkillManager() {
        super();

        addSkill(new Skill("Pick-pocket"));
        addSkill(new Skill("Detect/Remove Trap"));
        addSkill(new Skill("Creep"));
        addSkill(new Skill("Ranged Attack"));
    }

    public SneakSkillManager(Collection<Skill> skills) {
        super(skills);
    }
}
