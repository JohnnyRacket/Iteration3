package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by adamfortier on 4/11/16.
 */
public class OneHandedMeleeWeapon extends OneHandedWeapon {
    public OneHandedMeleeWeapon(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitOneHandedMeleeWeapon(this);
    }
}
