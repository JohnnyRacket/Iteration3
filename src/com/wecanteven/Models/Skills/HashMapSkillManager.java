package com.wecanteven.Models.Skills;

import com.wecanteven.Models.Abilities.Ability;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by simonnea on 4/8/16.
 */
public abstract class HashMapSkillManager extends SkillManager {
    HashMap<String, Skill> skills;

    public HashMapSkillManager() {
        super();
    }

    public HashMapSkillManager(Collection<Skill> skills) {
        super(skills);
    }

    @Override
    public Skill getSkill(Skill skill) {
        if (skills.containsKey(skill.getName())) {
            return skills.get(skill.getName());
        }

        throw new IllegalArgumentException("This occupation does not support the skill: " + skill.getName());
    }

    @Override
    public void configureAbility(Ability ability) {

    }

    @Override
    protected void addSkill(Skill skill) {
        if (!skills.containsKey(skill.getName())) {
            skills.put(skill.getName(), new Skill(skill.getName()));
        }
    }

    @Override
    protected void initStorage() {
        skills = new HashMap<>();
    }

    @Override
    public Iterator<Skill> getIterator() {
        return skills.values().iterator();
    }
}
