package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.StatsModifyUseable;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.MoneyItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Visitors.ItemStorageVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */

/**
 * DESIGN DEVIATION NOTES
 *
 * AbstractEquipment and AbstractInventory were changed to just be Equipment and Inventory which can be
 * subclassed into their respective implementing classes
 *
 * */
public class ItemStorage {
    private Character owner;

    private Inventory inventory;
    private Equipment equipped;
    private int maxInventoryCapacity;

    private int money;

    public ItemStorage(int maxInventoryCapacity){
        this.maxInventoryCapacity = maxInventoryCapacity;
        inventory = new TupleInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
        money = 0;
    }

    public ItemStorage(Character owner, int maxInventoryCapacity) {
        this.owner = owner;
        inventory = new TupleInventory(this, maxInventoryCapacity);
        this.maxInventoryCapacity = maxInventoryCapacity;
        inventory = new TupleInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
        money = 0;
    }

    public ItemStorage(Inventory inventory, Equipment equipment, Character owner) {
        this.inventory = inventory;
        this.equipped = equipment;
        this.owner = owner;
        money = 0;
    }

    public void addMoney(MoneyItem m) {
        money += m.getValue();
    }

    public boolean buy(int value) {
        if(value <= money) {
            money -= value;
            return true;
        }
        return false;
    }

    public void setMoney(int value) {
        money = value;
    }

    public MoneyItem getMoney() {
        return new MoneyItem(money);
    }

    /**
     *
     * Inventory Interface
     *
     * */

    public void addItem(TakeableItem item) {
        inventory.add(item);
    }

    public void addItem(TakeableItem item, int order) {
        inventory.add(item, order);
    }

    public void swap(int origin, int destination) { inventory.swap(origin, destination); }

    public void removeItem(TakeableItem item) {
        inventory.remove(item);
    }

    public TakeableItem removeItem(int order) { return inventory.remove(order); }

    public boolean hasItem(TakeableItem item) {
        return inventory.contains(item);
    }

    /**
     *
     * Equipment Interface
     *
     * */

    public boolean equip(EquipableItem item) {
        if (equipped.equip(item)) {
            owner.getStats().addStats(item.getStats());
            return true;
        }
        return false;
    }

    /**
     * Precondition: Item must be equipped
     * */
    public void unequip(EquipableItem item) {
        if (equipped.unequip(item))
            owner.getStats().subtractStats(item.getStats());
    }

    /**
     *
     * Consumption interface
     *
     * */

    /**
     * Precondition: Item must be in inventory
     * */
    public void use(StatsModifyUseable item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
            item.use(owner);
        }
    }

    /**
     *
     * Dropping interface
     *
     * */


    public void drop(TakeableItem item) {
        owner.drop(item);
    }

    /**
     *
     * Other methods
     *
     * */

    public void setOwner(Character character) {
        this.owner = character;
    }

    public int getMaxInventoryCapacity() {
        return maxInventoryCapacity;
    }

    public boolean inventoryIsFull() {
        return getInventory().isFull();
    }
    public void accept(ItemStorageVisitor visitor) {
        visitor.visitItemStorage(this);
        inventory.accept(visitor);
        equipped.accept(visitor);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipped() {
        return equipped;
    }
}
