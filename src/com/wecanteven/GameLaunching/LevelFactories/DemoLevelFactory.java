package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.Biomes.CustomBiome;
import com.wecanteven.AreaView.Biomes.DefaultBiome;
import com.wecanteven.AreaView.ViewObjects.Factories.*;
import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Controllers.AIControllers.ActionControllers.EnemyActionController;
import com.wecanteven.Controllers.AIControllers.ActionControllers.PetActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.EnemySearchingController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.PetSearchingController;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Factories.ItemMaps.ItemMap;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.NoInteractionStrategy;
import com.wecanteven.Models.Interactions.QuestDialogInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.OneHandedMeleeWeapon;
import com.wecanteven.Models.Items.Takeable.QuestedItem;
import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Enemy;
import com.wecanteven.Models.Occupation.Pet;
import com.wecanteven.Models.Quests.QuestableItemReward;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.*;

import java.util.ArrayList;


/**
 * Created by alexs on 4/14/2016.
 */
public class DemoLevelFactory extends LevelFactory {

    private int rOffset;
    private int sOffset;

    private final int mapWidth = 40;
    private final int mapLength = 40;
    private final int mapHeight = 22;

    private ArrayList<Location> desertLocations = new ArrayList<>();
    private ArrayList<Location> snowLocations = new ArrayList<>();
    private ArrayList<Location> fallLocations = new ArrayList<>();
    private ArrayList<Location> dirtyLocaions = new ArrayList<>();
    private ArrayList<Location> grassLocations = new ArrayList<>();

    private ArrayList<Location> biomePaint = fallLocations;

    Map map = new Map(mapWidth, mapLength, mapHeight);


    @Override
    public Map createMap() {
        map.setName("DemoLevelFactory");

        rOffset = 0;
        sOffset = 0;
        //Create a floor to work on
        (new FilledHex(getLocation(10,10,1), 10)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        map.getTile(10,10,1).setTerrain(new Water());

        //Base of ramp
        (new HexColumn(getLocation(7,11, 3), 6)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(5,12, 2), 3)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(4,13, 2), 3)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        (new HexColumn(getLocation(4,12, 2), 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(5,11, 2), 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(6,11, 2), 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new FilledHex(getLocation(7,13,2), 3)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new FilledHex(getLocation(6,13,3), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        (new FilledHex(getLocation(7,13,2), 3)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(4,11, 5), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(3,11, 6), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(2,11, 7), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(3,10, 7), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(4,9,8), 2)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(5,8, 7), 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(6,7, 5), 7)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(6,6, 2), 10)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        //SouthEast of ramp
        (new HexLine(getLocation(10,13,2), Direction.SOUTHEAST, 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexLine(getLocation(9,14,2), Direction.SOUTHEAST, 4)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        biomePaint = desertLocations;
        filled(7,19,2,5,groundMaker);
        filled(7,19,3,5,groundMaker);

        filled(9,20,2,5,groundMaker);
        filled(6,22,1,6,groundMaker);
        filled(12,23,0,6,groundMaker);
        filled(7,28,0,8,groundMaker);


        biomePaint = dirtyLocaions;
        //The desert pillars
        column(4,24,2,6,groundMaker);
        column(5,24,2,2,groundMaker);

        column(1,28,1,3,groundMaker);
        column(3,30,1,5,groundMaker);
        column(3,31,1,3,groundMaker);
        column(4,30,1,1,groundMaker);


        column(12,25,1,7,groundMaker);
        column(11,25,1,4,groundMaker);
        column(11,26,1,3,groundMaker);


        column(9,25,1,9,groundMaker);
        column(8,26,1,7,groundMaker);

        column(9,18,4,3,groundMaker);
        column(5,19,4,4,groundMaker);
        column(2,21,2,7,groundMaker);
        column(1,22,2,5,groundMaker);
        column(7,23,1,8,groundMaker);

        column(7,32,1,8,groundMaker);
        column(8,31,1,5,groundMaker);
        column(9,31,1,6,groundMaker);
        column(10,31,1,4,groundMaker);
        column(11,30,1,2,groundMaker);



        filled(18,15,0, 7, groundMaker);
        line(13,17,1, Direction.SOUTH, 4, groundMaker);



        //Mountain
        for (int i = 2; i < 13; i++) {
            (new FilledHex(getLocation(10,3,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }
        for (int i = 2; i < 15; i++) {
            (new FilledHex(getLocation(7,3,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }
        for (int i = 2; i < 14; i++) {
            (new FilledHex(getLocation(5,4,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }
        (new HexColumn(getLocation(13,2, 2), 13)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });
        (new HexColumn(getLocation(13,1, 2), 14)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        //River
        map.getTile(getLocation(7,2,14)).setTerrain(new Current(Direction.SOUTHEAST));
        map.getTile(getLocation(8,2,14)).setTerrain(new Current(Direction.SOUTHEAST));
        map.getTile(getLocation(9,2,14)).setTerrain(new Current(Direction.SOUTHEAST));
        map.getTile(getLocation(10,2,12)).setTerrain(new Current(Direction.SOUTHEAST));
        map.getTile(getLocation(11,2,12)).setTerrain(new Current(Direction.SOUTHEAST));
        map.getTile(getLocation(12,2,12)).setTerrain(new Current(Direction.SOUTH));
        //WATERFALL
        map.getTile(getLocation(12,3,12)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,11)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,10)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,9)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,8)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,7)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,6)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,5)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,3,4)).setTerrain(new Current(Direction.NORTHWEST));
        map.getTile(getLocation(12,3,3)).setTerrain(new Current(Direction.NORTHWEST));
        map.getTile(getLocation(12,3,2)).setTerrain(new Current(Direction.NORTHWEST));

        //MAKES TUNNEL
        map.getTile(getLocation(9, 4, 2)).setTerrain(new Air());
        map.getTile(getLocation(9, 4, 3)).setTerrain(new Air());
        map.getTile(getLocation(9, 4, 4)).setTerrain(new Air());

        for(int i = 8; i < 12; ++i){
            map.getTile(getLocation(i, 3, 2)).setTerrain(new Air());
            map.getTile(getLocation(i, 3, 3)).setTerrain(new Air());
            map.getTile(getLocation(i, 3, 4)).setTerrain(new Air());
        }
        //END OF MAKE TUNNEL

        //Clearing up portions
        (new HexColumn(getLocation(8,6, 2), 11)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Air());
        });
        (new HexColumn(getLocation(8,5, 2), 11)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Air());
        });


        //Lake
        (new FilledHex(getLocation(16,6,1), 6)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Water());
        });
        (new HexLine(getLocation(11,5,1), Direction.NORTHEAST, 4 )).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Water());
        });

        map.getTile(getLocation(12,4,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,5,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,6,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,7,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,8,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,9,1)).setTerrain(new Current(Direction.SOUTH));
        map.getTile(getLocation(12,10,1)).setTerrain(new Current(Direction.SOUTH));
        //smap.getTile(getLocation(12,11,1)).setTerrain(new Current(Direction.SOUTH));

        //sand under Lake
        biomePaint = desertLocations;
        filled(16,7,0,7, groundMaker);
        line(14 ,2, 0, Direction.SOUTHWEST, 4, groundMaker);



//        (new HexColumn(getLocation(7,13, 2), 6)).iterator().forEachRemaining( (location) -> {
//            map.getTile(location).setTerrain(new Ground());
//        });

        //Wetlands
        (new FilledHex(getLocation(20,10,0), 10)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        line(23,9,1,Direction.SOUTHWEST, 3, groundMaker);
        line(19,14,1,Direction.SOUTHWEST, 3, groundMaker);
        line(19,15,1,Direction.SOUTHWEST, 2, groundMaker);
        line(23,12,1,Direction.SOUTH, 4, groundMaker);

        point(24,10,1, groundMaker);




        //Marsh River
        (new HexLine(getLocation(21,7,0), Direction.SOUTHEAST, 7 )).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Water());
        });
        (new HexLine(getLocation(20,8,0), Direction.SOUTHEAST, 8 )).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Water());
        });
        line(21,9,0,Direction.SOUTH, 4, waterMaker);
        line(26,9,0,Direction.SOUTHWEST, 3, waterMaker);

        line(20,11,0,Direction.SOUTHWEST, 5, waterMaker);
        line(20,13,0,Direction.SOUTH, 2, waterMaker);
        line(26,10,0,Direction.SOUTH, 3, waterMaker);
        line(24,12,0,Direction.SOUTH, 2, waterMaker);
        line(20,12,0,Direction.SOUTHWEST, 5, waterMaker);
        filled(20,16,0,2,waterMaker);

        biomePaint = grassLocations;
        line(23,9,2,Direction.SOUTHWEST, 2, groundMaker);
        point(24,10,2, groundMaker);
        point(25,9,1, groundMaker);
        line(23,13,2,Direction.SOUTH, 2, groundMaker);
        line(22,17,1,Direction.NORTHWEST, 2, groundMaker);
        line(18,15,2,Direction.SOUTHEAST, 2, groundMaker);
        line(3,19,0,Direction.SOUTHWEST, 4, groundMaker);






        return map;
    }

    @Override
    public Biome createBiomes(ViewObjectFactory factory) {
        Biome plainsBiome = new DefaultBiome(new PlainsFactory(factory));

        Biome tundraBiome = new CustomBiome(new TundraFactory(factory), snowLocations, plainsBiome);
        Biome fallBiome = new CustomBiome(new AutumnFactory(factory), fallLocations, tundraBiome);
        Biome dirtyBiome = new CustomBiome(new DirtFactory(factory), dirtyLocaions, fallBiome);
        Biome desertBiome = new CustomBiome(new DesertFactory(factory), desertLocations, dirtyBiome);




        return desertBiome;
    }

    private void filled(int r, int s, int z, int size, TerrainMaker terrainMaker) {
        (new FilledHex(getLocation(r,s,z), size)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(terrainMaker.makeTerrain());
            biomePaint.add(location);
        });
    }

    private void line(int r, int s, int z, Direction d, int size, TerrainMaker terrainMaker) {
        (new HexLine(getLocation(r,s,z), d, size)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(terrainMaker.makeTerrain());
            biomePaint.add(location);
        });
    }
    private void column(int r, int s, int z, int size, TerrainMaker terrainMaker) {
        (new HexColumn(getLocation(r,s,z), size)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(terrainMaker.makeTerrain());
            biomePaint.add(location);
        });
    }

    private void point(int r, int s, int z, TerrainMaker terrainMaker) {
        map.getTile(getLocation(r,s,z)).setTerrain(terrainMaker.makeTerrain());
        biomePaint.add(getLocation(r,s,z));
    }

    private Location getLocation(int r, int s, int z) {
        return new Location(r+rOffset, s+sOffset, z);


    }

    @Override
    public void populateMap(Map map) {
        areasOfEffect(map);
        items(map);
        weaponNPC(map);
        mount(map);
        dialogNPC(map);
        tradeNPC(map);
        questNPC(map);
        petNPC(map);
    }

    public void mount(Map map) {
        Mount mount = new Mount(map, Direction.SOUTH);
        mount.setJumpHeight(5);
        System.out.println("ADDING THE MOUNT TO THE MAPPPPP");
        map.add(mount, new Location(16,10,2));
    }

    public void petNPC(Map map){
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy(), GameColor.PINK);
        npc.setOccupation(new Pet());
        PetSearchingController searchingController = new PetSearchingController(npc, map, 4);
        PetActionController actionController = new PetActionController(npc, map);
        AIController controller = new AIController(searchingController,actionController);
        npc.setController(controller);
        map.add(npc, new Location(9,9,2));
        ModelTime.getInstance().registerTickable(controller);
    }

    public void weaponNPC(Map map) {
        //"Creating an NPC and Giving him a chest Plate
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy(), GameColor.GRAY);
        npc.setOccupation(new Enemy());
        OneHandedMeleeWeapon i = new OneHandedMeleeWeapon("Katar", 50, new StatsAddable(0,0,0,0,0,0,0,0,0));
        EnemySearchingController esc = new EnemySearchingController(npc,map,3);
        EnemyActionController eac = new EnemyActionController(npc,map);
        AIController controller = new AIController(esc,eac);
        npc.setController(controller);
        npc.pickup(i);
        npc.equipItem(i);
        map.add(npc, new Location(7,3,15));
        ModelTime.getInstance().registerTickable(controller);
    }

    public void dialogNPC(Map map) {
        ArrayList<String> dialog = new ArrayList<>();
        dialog.add("Hello Avatar");
        dialog.add("You're an idiot and you're playing a dumb game");
        dialog.add("GTFO");
        NPC npc = new NPC(map, Direction.SOUTHWEST, new DialogInteractionStrategy(dialog), GameColor.YELLOW);
        map.add(npc, new Location(9,8,2));
    }

    public void questNPC(Map map) {
        QuestedItem questItem = new QuestedItem("Quest Item", 0);
        QuestableItemReward quest = new QuestableItemReward(questItem, ItemMap.getInstance().getItemAsTakeable("Top Hat"), new Location(18, 18, 1));
        ArrayList<String> startQuestDialog = new ArrayList<>();
        startQuestDialog.add("HELLO QUESTER!");
        startQuestDialog.add("This is an example of how quests work");
        startQuestDialog.add("Go and get the questable item!");

        ArrayList<String> endQuestDialog = new ArrayList<>();
        endQuestDialog.add("Oh! My! Lawd!");
        endQuestDialog.add("You found the item I was looking for!");
        endQuestDialog.add("Here, take this top hat! Goodbye!");

        QuestDialogInteractionStrategy questIter = new QuestDialogInteractionStrategy(startQuestDialog, endQuestDialog, quest);

        NPC npc = new NPC(map, Direction.SOUTHWEST, questIter, GameColor.PINK);
        map.add(npc, new Location(14,14,2));
    }

    public void tradeNPC(Map map) {
        NPC npc = new NPC(map, Direction.SOUTHEAST, new TradeInteractionStrategy(), GameColor.YELLOW);
        npc.pickup(new ChestEquipableItem("Buyable Chestplate", 5, null));
        npc.pickup(new ChestEquipableItem("Buyable Penis", 5, null));
        map.add(npc, new Location(13, 14, 2));
    }

    public void areasOfEffect(Map map) {
        TakeDamageAreaOfEffect tkdmgAoe = new TakeDamageAreaOfEffect(1);
        map.add(tkdmgAoe, new Location(1,16,2));

        TeleportAoe teleAoe = new TeleportAoe(new Location(7,4,15));
        map.add(teleAoe, new Location(2, 16, 2));

        HealingAreaOfEffect healAoe = new HealingAreaOfEffect(1);
        map.add(healAoe, new Location(3,16,2));

        LevelUpAoe levelUpAoe = new LevelUpAoe(300);
        map.add(levelUpAoe,new Location(4,16,2));

        InstaDeathAoe deathAoe = new InstaDeathAoe();
        map.add(deathAoe, new Location(5,16,2));
    }

    public void items(Map map) {

        //One shot
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Movement Buff"), new Location(1,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Minor Mana Orb"), new Location(2,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Mana Orb"), new Location(3,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Minor Health Orb"), new Location(4,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Health Orb"), new Location(5,15,3));


        map.add(ItemMap.getInstance().getItemAsTakeable("Fez"), new Location(11, 14,2));
        //Interactive Item??????
        /* TODO implement this */

        //Obstacle
        map.add(ItemMap.getInstance().getItemAsObstacle("Crate"), new Location(1,14,2));
        map.add(ItemMap.getInstance().getItemAsObstacle("Box"), new Location(2,14,2));

        //Equipable
        map.add(ItemMap.getInstance().getItemAsEquipable("Top Hat"), new Location(1,17,2));
        map.add(ItemMap.getInstance().getItemAsEquipable("Katar"), new Location(2,17,2));
        map.add(ItemMap.getInstance().getItemAsEquipable("Buyable Chestplate"), new Location(3,17,2));
        map.add(ItemMap.getInstance().getItemAsEquipable("Merp Boots"), new Location(4,17,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Box"), new Location(3, 14, 2));


        //Consumeable
    }

    private interface TerrainMaker {
        Terrain makeTerrain();
    }

    private TerrainMaker groundMaker = Ground::new;
    private TerrainMaker waterMaker = Water::new;

}
