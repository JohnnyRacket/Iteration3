package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class HeadEquipableItem extends EquipableItem
{
    public HeadEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
    }

    @Override
    public void equip(Equipment equipment) {
        equipment.equipHead(this);
    }

    @Override
    public void unequip(Equipment equipment) {
        equipment.unequipHead(this);
    }

    @Override
    public void accept(WeaponsVisitor visitor) {

    }
}
