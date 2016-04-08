package com.wecanteven.Models.Skills;

import java.util.Collection;

/**
 * Created by simonnea on 4/7/16.
 */
public class SummonerSkillManager extends HashMapSkillManager {

    public SummonerSkillManager() {
        super();

        addSkill(new Skill("Enchantment"));
        addSkill(new Skill("Boon"));
        addSkill(new Skill("Bane"));
        addSkill(new Skill("Staff Attack"));
    }

    public SummonerSkillManager(Collection<Skill> skills) {
        super(skills);
    }
}
