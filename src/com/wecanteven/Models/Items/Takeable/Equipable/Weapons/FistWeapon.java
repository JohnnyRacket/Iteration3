package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Factories.AbilityFactories.IAbilityCreateCommand;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.MeleeWeaponEquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by Alex on 4/16/2016.
 */
public class FistWeapon extends MeleeWeaponEquipableItem {
    public FistWeapon(String name, int value, StatsAddable stats) {
        super(name, value, stats);
        setCreateAbility((caster)->{
            return getFactory().vendBrawling(caster);
        });
    }

    @Override
    public void accept(WeaponsVisitor weaponsVisitor) {
        weaponsVisitor.visitFistWeapon(this);
    }
}
