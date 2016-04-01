package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public class HeadEquipableItem extends EquipableItem
{
    public HeadEquipableItem(String name, StatsAddable stats) {
        super(name, stats);
    }

    @Override
    public void equip(Character character) {
        character.equipHead(this);
    }
}
