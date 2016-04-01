package com.wecanteven.Models.Stats;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Stats {
    private int lives;
    private int strength,agility,intellect,hardiness,experience,movement;
    private int level,life,mana,offensiveRating,defensiveRating,armorRating;
    private int maxExperience,maxLife,maxMana;

    private ArrayList modifiedStats = new ArrayList<StatsAddables>();

    public Stats(){}
    public void modifyStats(StatsAddables addable){
        modifiedStats.add(addable);
    }

}
