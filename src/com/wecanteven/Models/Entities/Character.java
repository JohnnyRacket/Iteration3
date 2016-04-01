package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Items.*;
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
public class Character extends Entity implements Moveable,Positionable{
    Occupation occupation;
    Storage itemStorage, abilityStorage;
    Stats stats;
    public Character(){}
    public void attack(Direction d){}
    public void useAbility(int index){}

    /**
     * Equipment
     * */

    // TODO update this to pass storage to the item, so the only the specific item and the storage interact
    public void equipItem(EquipableItem item){
        item.equip(this);
    }

    /** Equip Head*/

    public void equipHead(HeadEquipableItem headItem) {}

    /** Equip Chest*/

    public void equipChest(ChestEquipableItem chestItem) {}

    /** Equip Boots*/

    public void equipBoots(BootsEquipableItem bootsItem) {}

    /** Equip Weapon*/

    public void equipWeapon(WeaponEquipableItem weaponItem) {}

    public void unequipItem(EquipableItem item){
        // Other stuff
    }
    private boolean equipAbility(String id){
        return false;
    }
    private boolean unequipAbility(String id){
        return false;
    }

    /**
     * Consumption
     * */
    public boolean consume(String id){
        return false;
    }
    public void drop(){}
    public void pickup(TakeableItem item){}
    public int getMovingTicks(){
        return 0;
    }
    public Location getLocation(){
        return this.getLocation();
    }
}
