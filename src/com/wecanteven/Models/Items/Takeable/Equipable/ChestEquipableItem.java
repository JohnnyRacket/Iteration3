package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;

/**
 * Created by simonnea on 3/31/16.
 */
public class ChestEquipableItem extends EquipableItem {
    public ChestEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        equipment.equipChest(this);
    }

    @Override
    public void unequip(Equipment equipment) {
        equipment.unequipChest(this);
    }
}
