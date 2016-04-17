package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.WeaponEquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by adamfortier on 4/12/16.
 */
public class TwoHandedWeapon extends WeaponEquipableItem {

    public TwoHandedWeapon(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitTwoHandWeapon(this);
    }
}
