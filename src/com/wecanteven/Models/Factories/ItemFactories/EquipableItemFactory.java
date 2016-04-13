package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Cachorrita & Josh on 4/6/2016.
 */
public class EquipableItemFactory {

    public BootsEquipableItem vendBoots(String name, int value, StatsAddable stats) {
        return new BootsEquipableItem(name, value, stats);
    }

    public ChestEquipableItem vendChestplate(String name, int value, StatsAddable stats) {
        return new ChestEquipableItem(name, value, stats);
    }

    public DualWieldMeleeWeapon vendDualMeleeWeapon(String name, int value, StatsAddable stats) {
        return new DualWieldMeleeWeapon(name, value, stats);
    }

    public DualWieldWeapon vendDualWieldWeapon(String name, int value, StatsAddable stats) {
        return new DualWieldWeapon(name, value, stats);
    }

    public HeadEquipableItem vendHeadEquipableItem(String name, int value, StatsAddable stats) {
        return new HeadEquipableItem(name, value, stats);
    }

    public MeleeWeaponEquipableItem vendMeleeWeaponEquipable(String name, int value, StatsAddable stats) {
        return new MeleeWeaponEquipableItem(name, value, stats);
    }

    public OneHandedMeleeWeapon vendOneHandedMeleeWeapon(String name, int value, StatsAddable stats) {
        return new OneHandedMeleeWeapon(name, value, stats);
    }

    public OneHandedRangedWeapon vendOneHandedRangedWeapon(String name, int value, StatsAddable stats) {
        return new OneHandedRangedWeapon(name, value, stats);
    }

    public OneHandedWeapon vendOneHandedWeapon(String name, int value, StatsAddable stats) {
        return new OneHandedWeapon(name, value, stats);
    }

    public RangedWeaponEquipableItem vendRangedWeaponEquipableItem(String name, int value, StatsAddable stats) {
        return new RangedWeaponEquipableItem(name, value, stats);
    }

    public TwoHandedWeapon vendTwoHandedWeapon(String name, int value, StatsAddable stats) {
        return new TwoHandedWeapon(name, value, stats);
    }

    public WeaponEquipableItem vendWeaponEquipableItem(String name, int value, StatsAddable stats) {
        return new WeaponEquipableItem(name, value, stats);
    }

}
