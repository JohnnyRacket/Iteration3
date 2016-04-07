package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.IOneShotItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.OneShotItemFactory;
import com.wecanteven.Models.Items.OneShot;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class OneShotItemMap {
    private static OneShotItemMap instance;

    private HashMap<String, IOneShotItemCreateCommand> itemMap;

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

        //TODO add various oneshot items
        itemMap.put("", () -> new OneShotItemFactory().vendDefaultOneShot());
    }

    public OneShot getItemAsOneShot(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no OneShot Item with name: " + name);
    }

    /**
     * Extensibility
     * */

    // TODO update to reference factory method
    protected void insertNewItem(String name, IOneShotItemCreateCommand itemCreationCommand) {
        itemMap.put(name, itemCreationCommand);
    }

    protected void removeItem(String name) {
        itemMap.remove(name);
    }
}
