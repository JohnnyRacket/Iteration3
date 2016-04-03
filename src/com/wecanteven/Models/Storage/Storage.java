package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

/**
 * Created by Brandon on 3/31/2016.
 */

/**
 * DESIGN DEVIATION NOTES
 *
 * An inventory and an equipment are essentially types of item storage, so the original AbstractInventory
 * and AbstractEquipment was refactored to provide item storage
 * */
public class Storage {
    private ItemStorage<TakeableItem> inventory;
    private ItemStorage<EquipableItem> equipped;

    public Storage(ItemStorage<TakeableItem> inventory, ItemStorage<EquipableItem> equipment)
    {
        this.inventory = inventory;
        this.equipped = equipment;
    }

    /**
     * Inventory Interface
     * */
    public boolean addItem(TakeableItem item) {
        return inventory.add(item);
    }

    public boolean removeItem(TakeableItem item) {
        return inventory.remove(item);
    }

    public boolean hasItem(TakeableItem item) {
        return inventory.hasItem(item);
    }

    /**
     * Equipment Interface
     * */

    public boolean equip(EquipableItem item) {
        return equipped.add(item);
    }

    public boolean unequip(EquipableItem item) {
        return equipped.remove(item);
    }

    public boolean isEquipped(EquipableItem item) {
        return equipped.hasItem(item);
    }

    /**
     * Consumption interface
     * */

    private boolean use(ConsumeableItem item) {
        return false;
    }
}
