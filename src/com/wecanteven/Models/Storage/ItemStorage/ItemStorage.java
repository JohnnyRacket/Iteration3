package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
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

    public ItemStorage(int maxInventoryCapacity){
        this.maxInventoryCapacity = maxInventoryCapacity;
        inventory = new TupleInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
    }

    public ItemStorage(Character owner, int maxInventoryCapacity) {
        this.owner = owner;

        inventory = new TupleInventory(this, maxInventoryCapacity);
        this.maxInventoryCapacity = maxInventoryCapacity;
        inventory = new TupleInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
    }

    public ItemStorage(Inventory inventory, Equipment equipment, Character owner) {
        this.inventory = inventory;
        this.equipped = equipment;
        this.owner = owner;
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

    public void equip(EquipableItem item) {
        if (equipped.equip(item))
            owner.getStats().addStats(item.getStats());
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
    public void use(ConsumeableItem item) {
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

    public void accept(ItemStorageVisitor visitor) {
        visitor.visitItemStorage(this);
        inventory.accept(visitor);
        equipped.accept(visitor);

    }
}
