package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;

/**
 * Created by adamfortier on 4/11/16.
 */
public class OneHandedWeapon extends WeaponEquipableItem {
    public OneHandedWeapon(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        super.equip(equipment);
    }

    @Override
    public void unequip(Equipment equipment) {
        super.unequip(equipment);
    }
}
