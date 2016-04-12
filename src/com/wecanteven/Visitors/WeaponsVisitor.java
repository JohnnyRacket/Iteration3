package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.Takeable.Equipable.*;

/**
 * Created by adamfortier on 4/7/16.
 */
public interface WeaponsVisitor {
    public void visitOneHandedWeapon(OneHandedWeapon oneHandedWeapon);
    public void visitDualWieldWeapon();
    public void visitDualWieldMeleeWeapon(DualWieldMeleeWeapon dualWieldMeleeWeapon);
    public void visitOneHandedMeleeWeapon(OneHandedMeleeWeapon oneHandedMeleeWeapon);
    public void visitOneHandedRangedWeapon(OneHandedRangedWeapon oneHandedRangedWeapon);
    public void visitWeapon(WeaponEquipableItem weapon);
}
