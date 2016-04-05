package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Visitors.EquipmentVisitor;

import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class Equipment extends StorageComponent {

    public Equipment(Storage owner) {
        super(owner);
    }

    public void accept(EquipmentVisitor visitor) {
        visitor.visitEquipment(this);
    }

    /**
     * Precondition: Item must be in inventory
     * */
    public void equip(EquipableItem item) {
        if (getOwner().hasItem(item)) {
            getOwner().removeItem(item);
            item.equip(this);
        }
    }

    /**
     * Precondition: Item must be equipped
     * */
    public void unequip(EquipableItem item) {
        if (isEquiped(item)) {
            getOwner().addItem(item);
            item.unequip(this);
        }
    }

    public abstract boolean isEquiped(EquipableItem item);

    public abstract boolean equipChest(ChestEquipableItem item);

    public abstract boolean equipBoots(BootsEquipableItem item);

    public abstract boolean equipHead(HeadEquipableItem item);

    public abstract boolean equipWeapon(WeaponEquipableItem item);

    public abstract boolean unequipChest(ChestEquipableItem item);

    public abstract boolean unequipBoots(BootsEquipableItem item);

    public abstract boolean unequipHead(HeadEquipableItem item);

    public abstract boolean unequipWeapon(WeaponEquipableItem item);

    public abstract Iterator<EquipableItem> getIterator();
}
