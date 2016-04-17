package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class WeaponEquipableItem extends EquipableItem {
    public WeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public final void equip(Equipment equipment) {
        equipment.equipWeapon(this);
    }

    @Override
    public final void unequip(Equipment equipment) {
        equipment.unequipWeapon(this);
    }

    public abstract void accept(WeaponsVisitor weaponsVisitor);
}
