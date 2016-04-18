package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.WeaponEquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class RangedWeaponEquipableItem extends WeaponEquipableItem {
    public RangedWeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
        setCreateAbility((caster)->{
            return getFactory().vendRangedWeapon(caster);
        });
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitRangedWeapon(this);
    }

    // Behaviors for ranged
}
