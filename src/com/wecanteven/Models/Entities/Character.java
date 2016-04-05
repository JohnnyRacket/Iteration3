package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity {
    private Occupation occupation;
    private ItemStorage itemItemStorage, abilityItemStorage;

    public Character(ActionHandler actionHandler, Direction direction){
        super(actionHandler, direction);
        occupation = new Smasher();
        stats = new Stats(this);
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, Stats stats){
        super(actionHandler, direction);
        this.occupation = occupation;
        this.stats = stats;
    }


    public void attack(Direction d){}
    public void useAbility(int index){}

    /**
     * Equipment
     * */
    public void equipItem(EquipableItem item){
        itemItemStorage.equip(item);
    }

    public void unequipItem(EquipableItem item){
        itemItemStorage.unequip(item);
    }

    /**
     * Inventory
     * */
    public void removeFromInventory(TakeableItem item) {
        itemItemStorage.removeItem(item);
        drop(item);
    }

    public void drop(TakeableItem item){
        // TODO notify map that item was dropped
    }
    public void pickup(TakeableItem item) {
        itemItemStorage.addItem(item);
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

    public Location getLocation(){
        return location;
    }
    public void levelUp(){
        stats.addStats(occupation.getStatsAddable());
    }
    public Occupation getOccupation() {return occupation; }

    @Override
    public void accept(EntityVisitor v) {
        v.visitCharacter(this);
    }
}
