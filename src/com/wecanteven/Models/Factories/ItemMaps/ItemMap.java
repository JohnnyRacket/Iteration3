package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.*;
import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.*;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by simonnea on 4/7/16.
 */
public class ItemMap {
    private static ItemMap itemMap;

    private HashMap<String, IObstacleItemCreateCommand> ObstacleMap;
    private HashMap<String, IOneShotItemCreateCommand> OneShotItemMap;
    private HashMap<String, IInteractiveItemCreateCommand> InteractiveitemMap;
    private HashMap<String, IEquipableItemCreateCommand> EquipableItemMap;
    private HashMap<String, ITakeableItemCreateCommand> TakeableItemMap;

    private HashSet<String> usedNames;

    private ItemMap() {
        ObstacleMap = new HashMap<>();
        OneShotItemMap = new HashMap<>();
        InteractiveitemMap = new HashMap<>();
        EquipableItemMap = new HashMap<>();
        TakeableItemMap = new HashMap<>();

        usedNames = new HashSet<>();

        initialize();
    }

    public ItemMap getInstance() {
        if (itemMap == null) {
            itemMap = new ItemMap();
        }

        return itemMap;
    }

    private void initialize() {
        initializeObstacle();
        initializeOneShot();
        initializeInteractive();
        initializeEquipable();
        initializeTakeable();
    }

    private void initializeObstacle() {
        ObstacleItemFactory factory = new ObstacleItemFactory();
    }

    private void initializeOneShot() {
        OneShotItemFactory factory = new OneShotItemFactory();
    }

    private void initializeInteractive() {
        InteractiveItemFactory factory = new InteractiveItemFactory();
    }

    private void initializeEquipable() {
        EquipableItemFactory factory = new EquipableItemFactory();
    }

    private void initializeTakeable() {
        TakeableItemFactory factory = new TakeableItemFactory();
    }

    public Obstacle getItemAsObstacle(String name) { return null; }

    public OneShot getItemAsOneShot(String name) { return null; }

    public InteractiveItem getItemAsInteractive(String name) { return null; }

    public EquipableItem getItemAsEquipable(String name) { return null; }

    public TakeableItem getItemAsTakeable(String name) { return null; }
    /**
     * Extensibility
     * */
    protected void insertNewItem(String name, IObstacleItemCreateCommand item) {
        ObstacleMap.put(name, item);
    }

    protected void insertNewItem(String name, IOneShotItemCreateCommand item) {
        OneShotItemMap.put(name, item);
    }

    protected void insertNewItem(String name, IInteractiveItemCreateCommand item) {
        InteractiveitemMap.put(name, item);
    }

    protected void insertNewItem(String name, IEquipableItemCreateCommand item) {
        EquipableItemMap.put(name, item);
    }

    protected void insertNewItem(String name, ITakeableItemCreateCommand item) {
        TakeableItemMap.put(name, item);
    }

    protected void removeItem(String name) {
        if (usedNames.contains(name)) {
            if (ObstacleMap.containsKey(name)) {
                ObstacleMap.remove(name);
                return;
            }

            if (OneShotItemMap.containsKey(name)) {
                OneShotItemMap.remove(name);
                return;
            }

            if (InteractiveitemMap.containsKey(name)) {
                InteractiveitemMap.remove(name);
                return;
            }

            if (EquipableItemMap.containsKey(name)) {
                EquipableItemMap.remove(name);
                return;
            }

            if (TakeableItemMap.containsKey(name)) {
                TakeableItemMap.remove(name);
            }
        }
    }
}
