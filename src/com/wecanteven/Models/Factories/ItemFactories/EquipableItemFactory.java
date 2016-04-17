package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.*;
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

    public HeadEquipableItem vendHeadEquipableItem(String name, int value, StatsAddable stats) {
        return new HeadEquipableItem(name, value, stats);
    }

    public RangedWeaponEquipableItem vendRangedWeaponEquipableItem(String name, int value, StatsAddable stats) {
        return new RangedWeaponEquipableItem(name, value, stats);
    }

    public TwoHandedWeapon vendTwoHandedWeapon(String name, int value, StatsAddable stats) {
        return new TwoHandedWeapon(name, value, stats);
    }

    public OneHandWeapon vendOneHandWeapon(String name, int value, StatsAddable stats) {
        return new OneHandWeapon(name, value, stats);
    }

    public FistWeapon vendFistWeapon(String name, int value, StatsAddable stats) {
        return new FistWeapon(name, value, stats);
    }

    public StaveWeapon vendStaveWeapon(String name, int value, StatsAddable stats) {
        return new StaveWeapon(name, value, stats);
    }
}
