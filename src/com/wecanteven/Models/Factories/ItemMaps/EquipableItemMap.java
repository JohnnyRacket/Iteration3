package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.IEquipableItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.EquipableItemFactory;
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

    private HashMap<String, IEquipableItemCreateCommand> itemMap;

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

        itemMap.put("", () -> new EquipableItemFactory().vendDefaultBootsInstance());
    }

    public TakeableItem getItemAsTakeable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no TakeableItem with name: " + name);
    }

    public EquipableItem getItemAsEquippable(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Equipable with name: " + name);
    }

    /**
     * Extensibility
     * */
    protected void addNewItem(String name, IEquipableItemCreateCommand createNewItem) {
        itemMap.put(name, createNewItem);
    }

    protected void removeItem(String name) {
        itemMap.remove(name);
    }
}
