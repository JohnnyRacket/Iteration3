package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.Takeable.Equipable.MeleeWeaponEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.RangedWeaponEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.WeaponEquipableItem;

/**
 * Created by adamfortier on 4/7/16.
 */
public interface WeaponsVisitor {
    public void visitRangedWeapon(RangedWeaponEquipableItem rangedWeapon);
    public void visitMeleeWeaponEquipableItem(MeleeWeaponEquipableItem meleeWeapon);
    public void visitWeapon(WeaponEquipableItem weapon);
}
