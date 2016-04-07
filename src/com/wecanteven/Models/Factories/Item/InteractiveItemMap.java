package com.wecanteven.Models.Factories.Item;

import com.wecanteven.Models.Items.InteractiveItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */

public final class InteractiveItemMap
{
    private static InteractiveItemMap instance;

    private HashMap<String, InteractiveItem> itemMap;

    private InteractiveItemMap() {
        itemMap = new HashMap<>();
        initialize();
    }

    public static InteractiveItemMap getInstance() {
        if (instance == null) {
            instance = new InteractiveItemMap();
        }

        return instance;
    }

    private void initialize() {
        itemMap.clear();

        itemMap.put("", new InteractiveItem(""));
    }

    public InteractiveItem getItemAsInteractive(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no InteractiveItem with name: " + name);
    }
}
