package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;
import com.wecanteven.Visitors.OccupationVisitor;

import java.util.*;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class Occupation implements OccupationVisitable{
    protected StatsAddable statsAddable;
    private CanMoveVisitor canMoveVisitor;

    private HashMap<Skill, Integer> skillMap = new HashMap<>();

    public Occupation() {
        initSkills();
    }

    public StatsAddable getStatsAddable() {
        return statsAddable;
    }

    public void setStatsAddable(StatsAddable statsAddable) {
        this.statsAddable = statsAddable;
    }

    public CanMoveVisitor getCanMoveVisitor() {
        return canMoveVisitor;
    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        this.canMoveVisitor = canMoveVisitor;
    }

    public void addSkillPoints(Skill skill, int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Cannot add a zero or negative skill point amount");
        } else if (skillMap.containsKey(skill)) {
            int newAmt = skillMap.get(skill) + amount;
            skillMap.replace(skill, newAmt);

            System.out.println("Added " + amount + " skill points to " + skill);
        } else {
            throw new IllegalArgumentException("This skill is not supported for this occupation: " + skill);
        }
    }
    public int getSkillPoints(Skill skill) {
        if (skillMap.containsKey(skill)) {
            return skillMap.get(skill);
        }
        return 0;
    }
    protected abstract void initSkills();
    protected boolean addSkill(Skill skill) {
        if (skillMap.containsKey(skill)) {
            return false;
        } else {
            skillMap.put(skill, 0);
            return true;
        }
    }

    public void accept(OccupationVisitor visitor) {
        visitor.visitOccupation(this);
    }

    public Iterator<Tuple<Skill, Integer>> getSkillIterator() {
        List<Tuple<Skill, Integer>> skillList = new ArrayList<>();

        for (Map.Entry<Skill, Integer> e : skillMap.entrySet()) {
            skillList.add(new Tuple<>(e.getKey(), e.getValue()));
        }

        return skillList.iterator();
    }
}
