package com.wecanteven.Models.Stats;

/**
 * Created by Brandon on 3/31/2016.
 */
public class StatsAddable {
    private PrimaryStat strength,agility,intellect, hardiness, experience, movement;
    private PrimaryStat lives,health,mana;

    public StatsAddable(int lives, int strength, int agility, int intellect, int hardiness, int experience, int movement, int health, int mana){
        this.lives = new PrimaryStat("Lives",lives);
        this.strength = new PrimaryStat("Strength",strength);
        this.agility = new PrimaryStat("Agility",agility);
        this.intellect = new PrimaryStat("Intellect",intellect);
        this.hardiness = new PrimaryStat("Hardiness",hardiness);
        this.experience = new PrimaryStat("Experience",experience);
        this.movement = new PrimaryStat("Movement",movement);
        this.health = new PrimaryStat("Health",health);
        this.mana = new PrimaryStat("Mana",mana);
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
    public int getHealth(){
        return health.getStat();
    }
    public int getMana(){
        return mana.getStat();
    }
}
