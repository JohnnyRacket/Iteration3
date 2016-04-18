package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by Alex on 4/16/2016.
 */
public class OneHandWeapon extends MeleeWeaponEquipableItem {
    public OneHandWeapon(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitOneHandWeapon(this);
    }
}
