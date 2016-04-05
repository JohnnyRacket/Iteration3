package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.Equipment;

/**
 * Created by simonnea on 3/31/16.
 */
public class BootsEquipableItem extends EquipableItem {
    public BootsEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        equipment.equipBoots(this);
    }

    @Override
    public void unequip(Equipment equipment) {
        equipment.unequipBoots(this);
    }
}
