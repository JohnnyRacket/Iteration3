package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Items.TakeableItem;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.Storage;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity {
    Occupation occupation;
    Storage itemStorage, abilityStorage;
    Stats stats;
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
    public void pickup(TakeableItem item){}
    public int getMovingTicks(){
        return 0;
    }
    public Location getLocation(){
        return location;
    }
}
