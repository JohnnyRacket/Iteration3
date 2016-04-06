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
        inventory = new HashTableInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
    }

    public ItemStorage(Character owner, int maxInventoryCapacity)
    {
        this.owner = owner;
        this.maxInventoryCapacity = maxInventoryCapacity;
        inventory = new HashTableInventory(this, maxInventoryCapacity);
        equipped = new HominidEquipment(this);
    }

    public ItemStorage(Inventory inventory, Equipment equipment, Character owner)
    {
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

    public void removeItem(TakeableItem item) {
        inventory.remove(item);
    }

    public boolean hasItem(TakeableItem item) {
        return inventory.contains(item);
    }

    // TODO does anything actually need this???
    public boolean isFull() { return inventory.isFull(); }

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
     * Precondition: Item must be in equipped
     * */
    public void unequip(EquipableItem item) {
        if (equipped.unequip(item))
            owner.getStats().subtractStats(item.getStats());
    }

    // TODO does anything actually need this???
    public boolean isEquipped(EquipableItem item) {
        return equipped.isEquiped(item);
    }

    /**
     *
     * Consumption interface
     *
     * */

    // TODO implement
    /**
     * Precondition: Item must be in inventory
     * */
    private boolean use(ConsumeableItem item) {

        return false;
    }

    /**
     *
     * Dropping interface
     *
     * */

    public int getMaxInventoryCapacity() {
        return maxInventoryCapacity;
    }
    public void drop(TakeableItem item) {
        owner.drop(item);
    }

    public void setOwner(Character character) {
        this.owner = character;
    }

    public void accept(ItemStorageVisitor visitor) {
        visitor.visitItemStorage(this);
        inventory.accept(visitor);
        equipped.accept(visitor);

    }
}
