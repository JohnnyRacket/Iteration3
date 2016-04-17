package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.*;

/**
 * Created by adamfortier on 4/7/16.
 */
public interface WeaponsVisitor {
    void visitFistWeapon(FistWeapon f);
    void visitOneHandWeapon(OneHandWeapon o);
    void visitRangedWeapon(RangedWeaponEquipableItem r);
    void visitStaveWeapon(StaveWeapon s);
    void visitTwoHandWeapon(TwoHandedWeapon t);
}
