package com.wecanteven.Models.Stats;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Observers.ModelObservable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.Visitors.StatsVisitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Stats implements Observer, ModelObservable, ViewObservable {
    private PrimaryStat strength,agility,intellect, hardiness, experience, movement;
    private PrimaryStat lives, level;
    private PrimaryStat currentHealth,currentMana;
    private Stat maxHealth,maxMana,offensiveRating,defensiveRating,armorRating;
    private Entity entity;
    private ArrayList<Observer> modelObservers = new ArrayList<>();
    private ArrayList<Observer> viewObservers = new ArrayList<>();

    public Stats(Entity entity){
        initStats(entity,1,1,1,1,30);
    }

    public void initStats(Entity entity, int strength,int agility,int intellect,int hardiness,int movement, int lives, int level){
        initStats(entity, strength, agility, intellect, hardiness, movement);
        this.lives.setStat(lives);
        this.level.setStat(level);
    }

    public void initStats(Entity entity, int strength,int agility,int intellect,int hardiness,int movement){
        this.entity = entity;
        this.strength = new PrimaryStat("Strength",strength);
        this.agility = new PrimaryStat("Agility",agility);
        this.intellect = new PrimaryStat("Intellect",intellect);
        this.hardiness = new PrimaryStat("Hardiness",hardiness);
        experience = new PrimaryStat("Experience",0);
        this.movement = new PrimaryStat("Movement",movement);

        level = new LevelStat(experience);
        level.modelAttach(this);

        maxHealth = new HealthStat(this.hardiness,level);
        maxMana = new ManaStat(this.intellect,level);
        offensiveRating = new OffensiveRatingStat(this.strength,level);
        defensiveRating = new DefensiveRatingStat(this.agility,level);
        armorRating = new ArmorRating(this.hardiness);

        currentHealth = new PrimaryStat("Health",maxHealth.getStat());
        currentMana = new PrimaryStat("Mana",maxMana.getStat());
        lives = new LivesStat(currentHealth,entity);
    }

    public void update(){
        entity.levelUp();
        refreshStats();
        modelNotifyObservers();
    }

    public void refreshStats(){
        currentHealth.setStat(maxHealth.getStat());
        currentMana.setStat(maxMana.getStat());
    }

    public void addStats(StatsAddable statsAddable){
        System.out.println("The entities Stats were modified" + statsAddable.getHealth());
        lives.add(statsAddable.getLives());
        strength.add(statsAddable.getStrength());
        agility.add(statsAddable.getAgility());
        intellect.add(statsAddable.getIntellect());
        hardiness.add(statsAddable.getHardiness());
        experience.add(statsAddable.getExperience());
        movement.add(statsAddable.getMovement());
        currentHealth.add(statsAddable.getHealth());
        currentMana.add(statsAddable.getMana());
        notifyObservers();
       // modelNotifyObservers();
    }

    public void subtractStats(StatsAddable statsAddable){
        lives.subtract(statsAddable.getLives());
        strength.subtract(statsAddable.getStrength());
        agility.subtract(statsAddable.getAgility());
        intellect.subtract(statsAddable.getIntellect());
        hardiness.subtract(statsAddable.getHardiness());
        experience.subtract(statsAddable.getExperience());
        movement.subtract(statsAddable.getMovement());
        currentHealth.subtract(statsAddable.getHealth());
        currentMana.subtract(statsAddable.getMana());
        notifyObservers();
       // modelNotifyObservers();
    }

    public void takeDamage(int damage) {
        if (damage > 0)
            currentHealth.subtract(damage);

        if (getHealth() <= 0) {
            loseLife();
            if (getLives() > 0)
                currentHealth.setStat(maxHealth.getStat());
        } else {
            if (getHealth() > getMaxHealth())
                currentHealth.setStat(getMaxHealth());
        }

        notifyObservers();
        modelNotifyObservers();
    }

    public void healDamage(int heal) {
        if (heal > 0)
            currentHealth.add(heal);

        if (getHealth() > getMaxHealth()) {
            currentHealth.setStat(getMaxHealth());
        }

        notifyObservers();
        modelNotifyObservers();
    }

    public void consumeMana(int amount) {
        if (amount > 0)
            currentMana.subtract(amount);

        if (getMana() <= 0)
            currentMana.setStat(0);

        notifyObservers();
        modelNotifyObservers();
    }

    public void restoreMana(int amount) {
        if (amount > 0)
            currentMana.add(amount);

        if (getMana() > getMaxMana())
            currentMana.setStat(getMaxMana());

        notifyObservers();
        modelNotifyObservers();
    }

    public void loseLife() {
        lives.subtract(1);

        notifyObservers();
        modelNotifyObservers();
    }

    //getters
    public int getLives(){ return lives.getStat();}
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

    public String toString(){
        String s = "";
        s += level.getName() + ": " + getLevel() + "\n";

        s += currentHealth.getName() + ": " + getHealth() + "/" + getMaxHealth() + "\n";
        s += currentMana.getName() + ": " + getMana() + "/" + getMaxMana() + "\n";
        s += experience.getName() + ": " + getExperience() + "\n";

        s += lives.getName() + ": " + getLives() + "\n";
        s += strength.getName() + ": " + getStrength() + "\n";
        s += agility.getName() + ": " + getAgility() + "\n";
        s += intellect.getName() + ": " + getIntellect() + "\n";
        s += hardiness.getName() + ": " + getHardiness() + "\n";
        s += movement.getName() + ": " + getMovement() + "\n";

        s += offensiveRating.getName() + ": " + getOffensiveRating() + "\n";
        s += defensiveRating.getName() + ": " + getDefensiveRating() + "\n";
        s += armorRating.getName() + ": " + getArmorRating() + "\n";
        return s;
    }

    @Override
    public ArrayList<Observer> getModelObservers() {
        return modelObservers;
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return viewObservers;
    }

    public void accept(StatsVisitor visitor) {
        visitor.visitStats(this);
    }
}
