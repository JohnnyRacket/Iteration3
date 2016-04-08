package com.wecanteven.Models.Skills;

/**
 * Created by simonnea on 4/7/16.
 */
public class Skill {
    private int skillPoints;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(String name, int skillPoints) {
        this.name = name;
        this.skillPoints = skillPoints;
    }

    public int getSkillPoints() {
        return this.skillPoints;
    }

    public void incrementSkillPoints() {
        skillPoints++;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // TODO ask dave
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return name.equals(skill.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
