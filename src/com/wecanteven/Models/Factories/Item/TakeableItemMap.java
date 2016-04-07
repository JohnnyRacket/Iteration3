package com.wecanteven.Models.Factories.Item;

import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class TakeableItemMap {
    private static TakeableItemMap instance;

    private HashMap<String, TakeableItem> itemMap;

    private TakeableItemMap() {
        itemMap = new HashMap<>();
        initialize();
    }

    public static TakeableItemMap getInstance() {
        if (instance == null) {
            instance = new TakeableItemMap();
        }

        return instance;
    }

    private void initialize() {
        itemMap.clear();

        itemMap.put("", new TakeableItem(""));
    }

    public TakeableItem getItemAsTakeable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no TakeableItem with name: " + name);
    }
}
