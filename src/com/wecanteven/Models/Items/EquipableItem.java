package com.wecanteven.Models.Items;

import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 3/31/16.
 */
public class EquipableItem extends TakeableItem {

    private StatsAddable stats;

    public EquipableItem(String name, StatsAddable stats) {
        super(name);

        this.stats = stats;
    }
}
