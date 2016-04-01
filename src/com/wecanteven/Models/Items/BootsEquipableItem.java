package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public class BootsEquipableItem extends EquipableItem {
    public BootsEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Character character) {
        character.equipBoots(this);
    }
}
