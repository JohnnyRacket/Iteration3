package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.ITakeableItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.TakeableItemFactory;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class TakeableItemMap {
    private static TakeableItemMap instance;

    private HashMap<String, ITakeableItemCreateCommand> itemMap;

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

        // TODO create takeable Items
        itemMap.put("", () -> new TakeableItemFactory().vendDefaultTakeableItem());
    }

    public TakeableItem getItemAsTakeable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no TakeableItem with name: " + name);
    }

    // TODO update to reference factory method
    protected void insertNewItem(String name, ITakeableItemCreateCommand itemCreationCommand) {
        itemMap.put(name, itemCreationCommand);
    }

    protected void removeItem(String name) {
        itemMap.remove(name);
    }
}
