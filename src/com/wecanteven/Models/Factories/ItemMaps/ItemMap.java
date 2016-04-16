package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.*;
import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.*;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.StatsAddable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    public static ItemMap getInstance() {
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

        ObstacleMap.put("Crate", () -> factory.vendObstacleItem("Crate"));
        ObstacleMap.put("Box", () -> factory.vendObstacleItem("Box"));


        usedNames.addAll(ObstacleMap.keySet());
    }

    private void initializeOneShot() {
        OneShotItemFactory factory = new OneShotItemFactory();

        OneShotItemMap.put("Box",
                () -> factory.vendOneShot("Box", new StatsAddable(0,0,0,0,0,0,0,0,0)));
        OneShotItemMap.put("Minor Health Orb",
                () -> factory.vendOneShot("Minor Health Orb", new StatsAddable(0,0,0,0,0,0,0,5,0)));
        OneShotItemMap.put("Minor Mana Orb",
                () -> factory.vendOneShot("Minor Mana Orb", new StatsAddable(0,0,0,0,0,0,0,0,5)));
        OneShotItemMap.put("Major Health Orb",
                () -> factory.vendOneShot("Major Health Orb", new StatsAddable(0,0,0,0,0,0,0,20,0)));
        OneShotItemMap.put("Major Mana Orb",
                () -> factory.vendOneShot("Major Mana Orb", new StatsAddable(0,0,0,0,0,0,0,0,20)));
        OneShotItemMap.put("Major Movement Buff",
                () -> factory.vendMovementSpeedBuffOneShot("Major Movement Buff", 60, 300));

        usedNames.addAll(OneShotItemMap.keySet());
    }

    private void initializeInteractive() {
        InteractiveItemFactory factory = new InteractiveItemFactory();

        InteractiveitemMap.put("Button", () -> factory.vendDefaultInteractiveItem("Button"));
        InteractiveitemMap.put("Box", () -> factory.vendDefaultInteractiveItem("Box"));

        usedNames.addAll(InteractiveitemMap.keySet());
    }

    private void initializeEquipable() {
        EquipableItemFactory factory = new EquipableItemFactory();
        //These are real items that are in the game: oh really....
        EquipableItemMap.put("Top Hat", () -> factory.vendHeadEquipableItem("Top Hat", 100, new StatsAddable(0,0,0,0,0,0,2,0,0)));
        EquipableItemMap.put("THE GAME CRASHER", () -> factory.vendHeadEquipableItem("THE GAME CRASHER", 1000, new StatsAddable(2,0,0,0,0,0,0,0,0)));
        EquipableItemMap.put("Katar", () -> factory.vendOneHandedMeleeWeapon("Katar", 50,  new StatsAddable(0,0,0,0,0,0,0,4,0)));

        EquipableItemMap.put("Buyable Chestplate", () -> factory.vendChestplate("Buyable Chestplate", 10,  new StatsAddable(0,0,0,0,0,0,0,4,0)));
        EquipableItemMap.put("Buyable Penis", () -> factory.vendChestplate("Buyable Penis", 2,  new StatsAddable(0,0,0,0,0,0,0,4,0)));

        EquipableItemMap.put("Merp Boots", () -> factory.vendBoots("Merp Boots", 3, new StatsAddable(0,0,0,0,0,0,10,0,0)));


        usedNames.addAll(EquipableItemMap.keySet());
    }

    private void initializeTakeable() {
        TakeableItemFactory factory = new TakeableItemFactory();

        TakeableItemMap.put("Key", () -> factory.vendTakeableItem("Key"));

        usedNames.addAll(TakeableItemMap.keySet());
    }

    public Obstacle getItemAsObstacle(String name) {
        if (ObstacleMap.containsKey(name)) {
            return ObstacleMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Obstacle item with name: " + name);
    }

    public OneShot getItemAsOneShot(String name) {
        if (OneShotItemMap.containsKey(name)) {
            return OneShotItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no OneShot item with name: " + name);
    }

    public InteractiveItem getItemAsInteractive(String name) {
        if (InteractiveitemMap.containsKey(name)) {
            return InteractiveitemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Interactive item with name: " + name);
    }

    public EquipableItem getItemAsEquipable(String name) {
        if (EquipableItemMap.containsKey(name)) {
            return EquipableItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Equipable item with name: " + name);
    }

    public TakeableItem getItemAsTakeable(String name) {
        if (EquipableItemMap.containsKey(name)) {
            return EquipableItemMap.get(name).create();
        }

        if (TakeableItemMap.containsKey(name)) {
            return TakeableItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Takeable item with name: " + name);
    }
    /**
     * Extensibility
     * */
    protected void insertNewItem(String name, IObstacleItemCreateCommand item) {
        if (!usedNames.contains(name))
            ObstacleMap.put(name, item);
        else
            throw new IllegalArgumentException("An item with this name exists already: " + name);
    }

    protected void insertNewItem(String name, IOneShotItemCreateCommand item) {
        if (!usedNames.contains(name))
            OneShotItemMap.put(name, item);
        else
            throw new IllegalArgumentException("An item with this name exists already: " + name);
    }

    protected void insertNewItem(String name, IInteractiveItemCreateCommand item) {
        if (!usedNames.contains(name))
            InteractiveitemMap.put(name, item);
        else
            throw new IllegalArgumentException("An item with this name exists already: " + name);
    }

    protected void insertNewItem(String name, IEquipableItemCreateCommand item) {
        if (!usedNames.contains(name))
            EquipableItemMap.put(name, item);
        else
            throw new IllegalArgumentException("An item with this name exists already: " + name);
    }

    protected void insertNewItem(String name, ITakeableItemCreateCommand item) {
        if (!usedNames.contains(name))
            TakeableItemMap.put(name, item);
        else
            throw new IllegalArgumentException("An item with this name exists already: " + name);
    }

    protected void removeItem(String name) {
        if (usedNames.contains(name)) {
            if (ObstacleMap.containsKey(name)) {
                ObstacleMap.remove(name);
            } else if (OneShotItemMap.containsKey(name)) {
                OneShotItemMap.remove(name);
            } else if (InteractiveitemMap.containsKey(name)) {
                InteractiveitemMap.remove(name);
            } else if (EquipableItemMap.containsKey(name)) {
                EquipableItemMap.remove(name);
            } else if (TakeableItemMap.containsKey(name)) {
                TakeableItemMap.remove(name);
            }

            usedNames.remove(name);
        }
    }
}
