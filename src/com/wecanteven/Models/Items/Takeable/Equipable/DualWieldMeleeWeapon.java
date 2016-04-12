package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by adamfortier on 4/12/16.
 */
public class DualWieldMeleeWeapon extends WeaponEquipableItem {

    public DualWieldMeleeWeapon(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitDualWieldMeleeWeapon(this);
    }
}
