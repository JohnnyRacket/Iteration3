package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.Equipment;

/**
 * Created by simonnea on 3/31/16.
 */
public class WeaponEquipableItem extends EquipableItem {
    public WeaponEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public boolean equip(Equipment equipment) {
        return equipment.equipWeapon(this);
    }
}
