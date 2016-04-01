package com.wecanteven.Models.Entities.CharacterStates;

import com.wecanteven.Models.Entities.CharacterStates.CharacterState;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class MountState extends CharacterState {
    public void attack(Direction d){}
    public void useAbility(int index){}
    public boolean equipItem(String id){
        return false;
    }
    public boolean unequipItem(String id){
        return false;
    }
    public boolean equipAbility(String id){
        return false;
    }
    public boolean unequipAbility(String id){
        return false;
    }
    public boolean consume(String id){
        return false;
    }
    public void drop(){}
    public void pickup(){}
    public void interactWith(){}
    public void mount(){}
    public void dismount(){}
}
