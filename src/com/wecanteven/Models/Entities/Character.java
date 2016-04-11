package com.wecanteven.Models.Entities;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.ItemStorageVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Character extends Entity {
    private Occupation occupation;
    private ItemStorage itemStorage, abilityItemStorage;
    private int windUpTicks, coolDownTicks;


    public Character(ActionHandler actionHandler, Direction direction){
        super(actionHandler, direction);
        occupation = new Smasher();
        this.itemStorage = new ItemStorage(this, 5);
        windUpTicks = 0;
        coolDownTicks = 0;
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, Stats stats){
        super(actionHandler, direction);
        this.occupation = occupation;
        setStats(stats);
        this.itemStorage = new ItemStorage(this, 5);
    }

    public Character(ActionHandler actionHandler, Direction direction, Occupation occupation, ItemStorage itemStorage){
        super(actionHandler, direction);
        this.occupation = occupation;
        this.itemStorage = itemStorage;
    }
    public void attack(Direction d){}
    public void useAbility(int index){
        System.out.println("An ability was used");
        Ability ability = new Ability(this);
        ability.cast();
    }

    /**
     * Equipment
     * */
    public void equipItem(EquipableItem item){
        itemStorage.equip(item);
    }

    public void unequipItem(EquipableItem item){
        itemStorage.unequip(item);
    }

    /**
     * Inventory
     * */
    public void removeFromInventory(TakeableItem item) {
        itemStorage.removeItem(item);
        drop(item);
    }

    public void drop(TakeableItem item){
        // TODO notify map that item was dropped

        if(getActionHandler().drop(item, this.getLocation())){
            itemStorage.removeItem(item);
        }
    }
    public void pickup(TakeableItem item) {
        itemStorage.addItem(item);
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

    @Override
    public void levelUp(){
        getStats().addStats(occupation.getStatsAddable());
    }
    public Occupation getOccupation() {return occupation; }

    @Override
    public void accept(EntityVisitor v) {
        v.visitCharacter(this);
    }

    public ItemStorage getItemStorage() {return itemStorage;}

    public void cast(ArrayList<Location> locations, StatsAddable effect){
        getActionHandler().useAbility(locations,effect);
    }

//    public void setWindUpTicks(int ticks){
//        this.windUpTicks = ticks;
//        if(ticks == 0){
//            setIsActive(false);
//            return;
//        }
//        setIsActive(true);
//        deIncrementTick();
//        modelNotifyObservers();
//    }
//    public void setCoolDownTicks(int ticks){
//        this.coolDownTicks = ticks;
//        if(ticks == 0){
//            setIsActive(false);
//            return;
//        }
//        setIsActive(true);
//        deIncrementTick();
//        modelNotifyObservers();
//    }
}
