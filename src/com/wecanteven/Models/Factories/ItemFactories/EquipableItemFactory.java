package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Takeable.Equipable.BootsEquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class EquipableItemFactory {
    public BootsEquipableItem vendDefaultBootsInstance() {
        return new BootsEquipableItem("Deez boots were made for walking", new StatsAddable(0,0,0,0,0,0,0,0,0));
    }
}
