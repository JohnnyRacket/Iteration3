package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.AbilityItemFactory;
import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.IAbilityItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.*;
import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.*;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.AbilityItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.Stats.StatsAddable;

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
    private HashMap<String, IUseableItemCreateCommand> UseableItemMap;
    private HashMap<String, IAbilityItemCreateCommand> AbilityItemMap;

    private HashSet<String> usedNames;

    private ItemMap() {
        ObstacleMap = new HashMap<>();
        OneShotItemMap = new HashMap<>();
        InteractiveitemMap = new HashMap<>();
        EquipableItemMap = new HashMap<>();
        TakeableItemMap = new HashMap<>();
        UseableItemMap = new HashMap<>();
        AbilityItemMap = new HashMap<>();

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
        initializeUseable();
        initializeAbility();
    }

    private void initializeObstacle() {
        ObstacleItemFactory factory = new ObstacleItemFactory();

        ObstacleMap.put("Crate", () -> factory.vendObstacleItem("Crate"));
        ObstacleMap.put("Box", () -> factory.vendObstacleItem("Box"));


        usedNames.addAll(ObstacleMap.keySet());
    }

    private void initializeOneShot() {
        OneShotItemFactory factory = new OneShotItemFactory();

        OneShotItemMap.put("Gray",
                () -> factory.vendOneShot("Gray", new StatsAddable(0,0,0,0,0,0,0,0,0)));
        OneShotItemMap.put("Red",
                () -> factory.vendOneShot("Red", new StatsAddable(0,0,0,0,0,0,0,10,0)));
        OneShotItemMap.put("Blue",
                () -> factory.vendOneShot("Blue", new StatsAddable(0,0,0,0,0,0,0,0,20)));
         OneShotItemMap.put("Orange",
                 () -> factory.vendMovementSpeedBuffOneShot("Orange", 60, 300));
        OneShotItemMap.put("Purple",
                () -> factory.vendFlyingBuffOneShot("Purple", 600));
        OneShotItemMap.put("Green",
                () -> factory.vendMovementStunBuffOneShot("Green", 200));

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
        //Real Shit right here

        EquipableItemMap.put("Antenna", () -> factory.vendHeadEquipableItem("Antenna", 100, new StatsAddable(0,0,0,5,0,0,0,0,0)));
        EquipableItemMap.put("Fertilizer", () -> factory.vendHeadEquipableItem("Fertilizer", 100, new StatsAddable(0,0,0,0,5,0,0,0,0)));
        EquipableItemMap.put("Halo", () -> factory.vendHeadEquipableItem("Halo", 100, new StatsAddable(0,5,0,0,0,0,0,0,0)));

        EquipableItemMap.put("Expand", () -> factory.vendFistWeapon("Expand", 100, new StatsAddable(0,10000,0,0,0,0,0,0,0)));
        EquipableItemMap.put("Flare", () -> factory.vendFistWeapon("Flare", 100, new StatsAddable(0,10,0,0,0,0,0,0,0)));
        EquipableItemMap.put("Flash", () -> factory.vendFistWeapon("Flash", 100, new StatsAddable(0,10,0,0,0,0,0,0,0)));

        EquipableItemMap.put("Sword", () -> factory.vendOneHandWeapon("Sword", 100, new StatsAddable(0,15,0,0,0,0,0,0,0)));

        EquipableItemMap.put("Club", () -> factory.vendTwoHandedWeapon("Club", 100, new StatsAddable(0,15,0,0,0,0,0,0,0)));

        EquipableItemMap.put("Arcane", () -> factory.vendStaveWeapon("Arcane", 100, new StatsAddable(0,15,0,0,0,0,0,0,0)));

        EquipableItemMap.put("Blaster", () -> factory.vendRangedWeaponEquipableItem("Blaster", 100, new StatsAddable(0,15,0,0,0,0,0,0,0)));


        //These are real items that are in the game: oh really....
//        EquipableItemMap.put("Top Hat", () -> factory.vendHeadEquipableItem("Top Hat", 100, new StatsAddable(0,0,0,0,0,0,2,0,0)));
//        EquipableItemMap.put("THE GAME CRASHER", () -> factory.vendHeadEquipableItem("THE GAME CRASHER", 1000, new StatsAddable(2,0,0,0,0,0,0,0,0)));
//        EquipableItemMap.put("Katar", () -> factory.vendOneHandedMeleeWeapon("Katar", 50,  new StatsAddable(0,0,0,0,0,0,0,4,0)));
//
//        EquipableItemMap.put("Buyable Chestplate", () -> factory.vendChestplate("Buyable Chestplate", 10,  new StatsAddable(0,0,0,0,0,0,0,4,0)));
//        EquipableItemMap.put("Buyable Penis", () -> factory.vendChestplate("Buyable Penis", 2,  new StatsAddable(0,0,0,0,0,0,0,4,0)));
//
//        EquipableItemMap.put("Merp Boots", () -> factory.vendBoots("Merp Boots", 3, new StatsAddable(0,0,0,0,0,0,10,0,0)));
//
//        EquipableItemMap.put("Fez", () -> factory.vendHeadEquipableItem("Fez", 69, new StatsAddable(1,1,1,1,1,1,1,1,1)));

        usedNames.addAll(EquipableItemMap.keySet());
    }

    private void initializeTakeable() {
        TakeableItemFactory factory = new TakeableItemFactory();

        TakeableItemMap.put("Key", () -> factory.vendTakeableItem("Key"));

        usedNames.addAll(TakeableItemMap.keySet());
    }

    private void initializeUseable() {
        UseableItemFactory factory = new UseableItemFactory();

        UseableItemMap.put("Brilliance",
                () -> factory.vendStatModifyingBuffItem("Brilliance", 25, new StatsAddable(0,0,0,15,0,0,0,0,0)));
        UseableItemMap.put("Spinach",
                () -> factory.vendStatModifyingBuffItem("Spinach", 25, new StatsAddable(0,15,0,0,0,0,0,0,0)));
        UseableItemMap.put("RedBull",
                () -> factory.vendFlyingBuffItem("RedBull", 100));

        usedNames.addAll(UseableItemMap.keySet());
    }

    private void initializeAbility() {
        AbilityItemFactory factory = new AbilityItemFactory();

        AbilityItemMap.put("One-handed weapon", () -> factory.vendOneHandedWeapon("One-handed weapon", 100));
        AbilityItemMap.put("Two-handed weapon", () -> factory.vendTwoHandedWeapon("Two-handed weapon", 100));
        AbilityItemMap.put("Brawling", () -> factory.vendBrawling("Brawling", 100));
        AbilityItemMap.put("BindWounds", () -> factory.vendBindWounds("BindWounds", 100));

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

        if (UseableItemMap.containsKey(name)) {
            return UseableItemMap.get(name).create();
        }

        if (TakeableItemMap.containsKey(name)) {
            return TakeableItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Takeable item with name: " + name);
    }

    public UseableItem getItemAsUseable(String name) {
        if (UseableItemMap.containsKey(name)) {
            return UseableItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Useable item with name: " + name);
    }

    public AbilityItem getItemAsAbility(String name) {
        if (AbilityItemMap.containsKey(name)) {
            return AbilityItemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Ability item with name: " + name);
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
