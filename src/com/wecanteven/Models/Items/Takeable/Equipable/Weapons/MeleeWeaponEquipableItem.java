package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.WeaponEquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class MeleeWeaponEquipableItem extends WeaponEquipableItem {
    public MeleeWeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    // Behaviors for Melee + subclasses for one handed/two handed
}
