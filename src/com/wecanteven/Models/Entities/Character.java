package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.Storage;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity {
    private Occupation occupation;
    private Storage itemStorage, abilityStorage;

    public Character(ActionHandler actionHandler, Direction direction){
        super(actionHandler, direction);
        occupation = new Smasher();
        stats = new Stats(this);
    }


    public void attack(Direction d){}
    public void useAbility(int index){}

    /**
     * Equipment
     * */

    // TODO update this to pass storage to the item, so the only the specific item and the storage interact
    public void equipItem(EquipableItem item){
        itemStorage.equip(item);
    }

    public void unequipItem(EquipableItem item){
        itemStorage.unequip(item);
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
        return location;
    }
    public void levelUp(){
        stats.modifyStats(occupation.getStatsAddable());
    }

    @Override
    public void accept(EntityVisitor v) {
        v.visitCharacter(this);
    }
}
