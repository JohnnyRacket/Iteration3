package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class EquipableItemFactory {
    public BootsEquipableItem vendMovementSpeedBoots(String name, int speedIncrease) {
        return new BootsEquipableItem(name, 50, new StatsAddable(0,0,0,0,0,0,speedIncrease,0,0));
    }

    public BootsEquipableItem vendArmorBoots(String name, int armorIncrease) {
        int newMovementSpeed = armorIncrease/4 * -1;

        return new BootsEquipableItem(name, 25, new StatsAddable(0,0,0,0,armorIncrease,0,newMovementSpeed,0,0));
    }

    public ChestEquipableItem vendChestplate(String name, int agilityIncrease) {
        return new ChestEquipableItem(name, 30, new StatsAddable(0,0,agilityIncrease,0,0,0,0,0,0));
    }

    public HeadEquipableItem vendIntellectCap(String name, int intellectIncrease) {
        int newDefensive = intellectIncrease/3 * -1;

        return new HeadEquipableItem(name, 15, new StatsAddable(0,0,newDefensive,intellectIncrease,0,0,0,0,0));
    }

    public HeadEquipableItem vendHelmet(String name, int defensiveIncrease) {
        return new HeadEquipableItem(name, 14, new StatsAddable(0,0,defensiveIncrease,0,0,0,0,0,0));
    }

    public RangedWeaponEquipableItem vendDefaultRangedWeapon(String name) {
        return new RangedWeaponEquipableItem(name, 100, new StatsAddable(0,0,0,0,0,0,0,0,0));
    }

    public MeleeWeaponEquipableItem vendDefaultMeleeWeapon(String name) {
        return new MeleeWeaponEquipableItem(name, 10, new StatsAddable(0,0,0,0,0,0,0,0,0));
    }
}
