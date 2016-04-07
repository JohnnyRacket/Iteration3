package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.IInteractiveItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.InteractiveItemFactory;
import com.wecanteven.Models.Items.InteractiveItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */

public class InteractiveItemMap
{
    private static InteractiveItemMap instance;

    private HashMap<String, IInteractiveItemCreateCommand> itemMap;

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

        // TODO flesh out real items
        itemMap.put("", () -> new InteractiveItemFactory().vendDefaultInteractiveItem());
    }

    public InteractiveItem getItemAsInteractive(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no InteractiveItem with name: " + name);
    }

    /**
     * Extensibility
     * */
    protected void insertNewItem(String name, IInteractiveItemCreateCommand item) {
        itemMap.put(name, item);
    }

    protected void removeItem(String name) {
        itemMap.remove(name);
    }
}
