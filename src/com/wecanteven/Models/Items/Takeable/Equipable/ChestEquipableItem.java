package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public class ChestEquipableItem extends EquipableItem {
    public ChestEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Character character) {
        character.equipChest(this);
    }
}
