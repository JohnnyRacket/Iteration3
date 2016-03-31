package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity{
    Occupation occupation;
    public Character(){}
    private void attack(Direction d){}
    private void useAbility(int index){}
    private boolean equipItem(String id){
        return false;
    }
    private boolean unequipItem(String id){
        return false;
    }
    private boolean equipAbility(String id){
        return false;
    }
    private boolean unequipAbility(String id){
        return false;
    }
    private boolean consume(String id){
        return false;
    }
    private void drop(){}
    private void pickup(){}
}
