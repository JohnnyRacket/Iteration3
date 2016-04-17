package com.wecanteven.Models.Factories.ItemFactories.CreateCommands;

import com.wecanteven.Models.Items.Takeable.StatsModifyUseable;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by simonnea on 4/17/16.
 */
public class UseableItemFactory {
    public StatsModifyUseable vendStatModifyingBuffItem(String name, int value, StatsAddable modifier) {
        return new StatsModifyUseable(name, value, modifier);
    }
}
