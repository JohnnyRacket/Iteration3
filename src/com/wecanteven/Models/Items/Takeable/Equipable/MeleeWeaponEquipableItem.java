package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public class MeleeWeaponEquipableItem extends WeaponEquipableItem {
    public MeleeWeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    // Behaviors for Melee + subclasses for one handed/two handed
}
