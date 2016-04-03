package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Visitors.EquipmentVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class Equipment extends ItemStorage<EquipableItem> {

    public void accept(EquipmentVisitor visitor) {
        visitor.visitEquipment(this);
    }

    public boolean equip(EquipableItem item) {
        return item.equip(this);
    }

    public abstract boolean equipChest(ChestEquipableItem item);

    public abstract boolean equipBoots(BootsEquipableItem item);

    public abstract boolean equipHead(HeadEquipableItem item);

    public abstract boolean equipWeapon(WeaponEquipableItem item);
}
