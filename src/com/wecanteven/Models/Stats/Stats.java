package com.wecanteven.Models.Stats;

import com.wecanteven.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Stats {
    private PrimaryStat strength,agility,intellect, hardiness, experience, movement;
    private PrimaryStat lives,currentHealth,currentMana;
    private Stat maxHealth,maxMana,offensiveRating,defensiveRating,armorRating;
    private LevelStat level;

    public Stats(int strength,int agility,int intellect,int hardiness,int movement){
        this.strength = new PrimaryStat(strength);
        this.agility = new PrimaryStat(agility);
        this.intellect = new PrimaryStat(intellect);
        this.hardiness = new PrimaryStat(hardiness);
        experience = new PrimaryStat(0);
        this.movement = new PrimaryStat(movement);

        level = new LevelStat(experience);
        maxHealth = new HealthStat(this.hardiness,level);
        maxMana = new ManaStat(this.intellect,level);
        offensiveRating = new OffensiveRatingStat(this.strength,level);
        defensiveRating = new DefensiveRatingStat(this.agility,level);
        armorRating = new ArmorRating(this.hardiness);

        lives = new PrimaryStat(3);
        currentHealth = new PrimaryStat(maxHealth.getStat());
        currentMana = new PrimaryStat(maxMana.getStat());
    }

    public void modifyStats(StatsAddable statsAddable){
        lives.add(statsAddable.getLives());
        strength.add(statsAddable.getStrength());
        agility.add(statsAddable.getAgility());
        intellect.add(statsAddable.getIntellect());
        hardiness.add(statsAddable.getHardiness());
        experience.add(statsAddable.getExperience());
        movement.add(statsAddable.getMovement());
        currentHealth.add(statsAddable.getHealth());
        currentMana.add(statsAddable.getMana());
    }

    //getters
    public int getLives(){
        return lives.getStat();
    }
    public int getStrength(){
        return strength.getStat();
    }
    public int getAgility(){
        return agility.getStat();
    }
    public int getIntellect(){
        return intellect.getStat();
    }
    public int getHardiness(){
        return hardiness.getStat();
    }
    public int getExperience(){
        return experience.getStat();
    }
    public int getMovement(){
        return movement.getStat();
    }
    public int getLevel(){
        return level.getStat();
    }
    public int getHealth(){
        return currentHealth.getStat();
    }
    public int getMana(){
        return currentMana.getStat();
    }
    public int getOffensiveRating(){
        return offensiveRating.getStat();
    }
    public int getDefensiveRating(){
        return defensiveRating.getStat();
    }
    public int getArmorRating(){
        return armorRating.getStat();
    }
    public int getMaxHealth(){
        return maxHealth.getStat();
    }
    public int getMaxMana(){
        return maxMana.getStat();
    }
}
