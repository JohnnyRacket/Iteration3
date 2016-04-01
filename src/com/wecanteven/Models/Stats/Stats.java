package com.wecanteven.Models.Stats;

import com.wecanteven.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Stats {
    private StatsAddable baseStats, modifiedStats;
    private int level,maxHealth,maxMana,offensiveRating,defensiveRating,armorRating;
    private int maxExperience,currentHealth,currentMana;
    private ArrayList buffedStats;
    private Entity entity;

    //constructor sets the reference to the entity that holds it
    public Stats(Entity entity){
        buffedStats  = new ArrayList<StatsAddable>();
        this.entity = entity;
    }

    public void modifyStats(StatsAddable addable){
        modifiedStats.sum(addable);
    }

    public void addBuff(StatsAddable addable){
        buffedStats.add(addable);
        modifiedStats.sum(addable);
    }
    public void removeBuff(StatsAddable addable){
        buffedStats.remove(addable);
        modifiedStats.subtract(addable);
    }

    //permanately adds to the baseStats object
    public void levelUp(StatsAddable levelStats){
        baseStats.sum(levelStats);
        modifiedStats.sum(levelStats);
    }

    //getters
    public StatsAddable getBaseStats(){
        return baseStats;
    }
    public int getLives(){
        return modifiedStats.getLives();
    }
    public int getStrength(){
        return modifiedStats.getStrength();
    }
    public int getIntellect(){
        return modifiedStats.getIntellect();
    }
    public int getHardiness(){
        return modifiedStats.getHardiness();
    }
    public int getExperience(){
        return modifiedStats.getExperience();
    }
    public int getMovement(){
        return modifiedStats.getMovement();
    }
    public int getLevel(){
        return level;
    }
    public int getHealth(){
        return currentHealth;
    }
    public int getMana(){
        return currentMana;
    }
    public int getOffensiveRating(){
        return offensiveRating;
    }
    public int getDefensiveRating(){
        return defensiveRating;
    }
    public int getArmorRating(){
        return armorRating;
    }
    public int getMaxExperience(){
        return maxExperience;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getMaxMana(){
        return maxMana;
    }
}
