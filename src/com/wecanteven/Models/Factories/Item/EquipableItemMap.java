package com.wecanteven.Models.Factories.Item;

import com.wecanteven.Models.Items.Takeable.Equipable.BootsEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.StatsAddable;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class EquipableItemMap {
    private static EquipableItemMap instance;

    private HashMap<String, EquipableItem> itemMap;

    private EquipableItemMap() {
        itemMap = new HashMap<>();
        initialize();
    }

    public static EquipableItemMap getInstance() {
        if (instance == null) {
            instance = new EquipableItemMap();
        }

        return instance;
    }

    private void initialize() {
        itemMap.clear();

        itemMap.put("", new BootsEquipableItem("", new StatsAddable(0,0,0,0,0,0,0,0,0)));
    }

    public TakeableItem getItemAsTakeable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no TakeableItem with name: " + name);
    }

    public EquipableItem getItemAsEquippable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no Equipable with name: " + name);
    }
}
