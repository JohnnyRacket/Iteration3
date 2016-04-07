package com.wecanteven.Models.Factories.Item;

import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class OneShotItemMap {
    private static OneShotItemMap instance;

    private HashMap<String, OneShot> itemMap;

    private OneShotItemMap() {
        itemMap = new HashMap<>();
        initialize();
    }

    public static OneShotItemMap getInstance() {
        if (instance == null) {
            instance = new OneShotItemMap();
        }

        return instance;
    }

    private void initialize() {
        itemMap.clear();

        //TODO alex is this right?
        itemMap.put("", new OneShot("", (entity) -> entity.die()));
    }

    public OneShot getItemAsOneShot(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no OneShot Item with name: " + name);
    }
}
