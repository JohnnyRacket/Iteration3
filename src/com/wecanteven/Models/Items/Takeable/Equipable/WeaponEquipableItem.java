package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;

/**
 * Created by simonnea on 3/31/16.
 */
public class WeaponEquipableItem extends EquipableItem {
    public WeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        equipment.equipWeapon(this);
    }

    @Override
    public void unequip(Equipment equipment) {
        equipment.unequipWeapon(this);
    }
}
