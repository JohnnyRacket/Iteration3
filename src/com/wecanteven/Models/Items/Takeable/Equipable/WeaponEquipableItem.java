package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class WeaponEquipableItem extends EquipableItem {
    public WeaponEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        equipment.equipWeapon(this);
    }

    @Override
    public void unequip(Equipment equipment) {
        equipment.unequipWeapon(this);
    }

    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitWeapon(this);
    }
}
