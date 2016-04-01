package com.wecanteven.Models.Stats;

/**
 * Created by Brandon on 3/31/2016.
 */
public class StatsAddable {
    private int lives;
    private int strength,agility,intellect,hardiness,experience,movement;
    private int health,mana;

    public StatsAddable(int lives, int strength, int agility, int intellect, int hardiness, int experience, int movement, int health, int mana){
        this.lives = lives;
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.hardiness = hardiness;
        this.experience = experience;
        this.movement = movement;
        this.health = health;
        this.mana = mana;
    }

    public void sum(StatsAddable other){
        lives += other.getLives();
        strength += other.getStrength();
        agility += other.getAgility();
        intellect += other.getIntellect();
        hardiness += other.getHardiness();
        experience += other.getExperience();
        movement += other.getMovement();
        health += other.getHealth();
        mana += other.getMana();
    }
    public void subtract(StatsAddable other){
        lives -= other.getLives();
        strength -= other.getStrength();
        agility -= other.getAgility();
        intellect -= other.getIntellect();
        hardiness -= other.getHardiness();
        experience -= other.getExperience();
        movement -= other.getMovement();
        health -= other.getHealth();
        mana -= other.getMana();
    }

    //getters
    public int getLives(){
        return lives;
    }
    public int getStrength(){
        return strength;
    }
    public int getAgility() { return agility; }
    public int getIntellect(){
        return intellect;
    }
    public int getHardiness(){
        return hardiness;
    }
    public int getExperience(){
        return experience;
    }
    public int getMovement(){
        return movement;
    }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
}
