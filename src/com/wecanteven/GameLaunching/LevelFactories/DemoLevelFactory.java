package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.Biomes.CustomBiome;
import com.wecanteven.AreaView.Biomes.DefaultBiome;
import com.wecanteven.AreaView.ViewObjects.Factories.*;
import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Controllers.AIControllers.AITime;
import com.wecanteven.Controllers.AIControllers.ActionControllers.EnemyActionController;
import com.wecanteven.Controllers.AIControllers.ActionControllers.PetActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.EnemySearchingController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.PetSearchingController;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Factories.ItemMaps.ItemMap;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.NoInteractionStrategy;
import com.wecanteven.Models.Interactions.QuestDialogInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.FistWeapon;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.OneHandWeapon;
import com.wecanteven.Models.Items.Takeable.QuestedItem;
import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Enemy;
import com.wecanteven.Models.Occupation.Friendly;
import com.wecanteven.Models.Occupation.Pet;
import com.wecanteven.Models.Quests.QuestableItemReward;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.*;
import com.wecanteven.Visitors.CanFallVisitors.FlyingCanFallVisitor;

import java.util.ArrayList;


/**
 * Created by alexs on 4/14/2016.
 */
public class DemoLevelFactory extends LevelFactory {

    private int rOffset;
    private int sOffset;
    private int zOffset;

    private final int mapWidth = 40;
    private final int mapLength = 40;
    private final int mapHeight = 22;

    private ArrayList<Location> desertLocations = new ArrayList<>();
    private ArrayList<Location> snowLocations = new ArrayList<>();
    private ArrayList<Location> fallLocations = new ArrayList<>();
    private ArrayList<Location> dirtyLocaions = new ArrayList<>();
    private ArrayList<Location> grassLocations = new ArrayList<>();

    private ArrayList<Location> biomePaint = fallLocations;

    Map map;


    public DemoLevelFactory() {
        map = new Map(mapWidth, mapLength, mapHeight);
    }


    @Override
    public Map createMap() {
        map.setName("DemoLevelFactory");
        System.out.println("Setting Terrain BIOME");
        rOffset = 0;
        sOffset = 0;
        zOffset = 1;
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

        // Quest
        column(19,5,1, 8, groundMaker);

        //Mountain
        for (int i = 1; i < 13; i++) {
            (new FilledHex(getLocation(10,3,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }

        for (int i = 1; i < 15; i++) {
            (new FilledHex(getLocation(7,3,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }
        for (int i = 1; i < 14; i++) {
            (new FilledHex(getLocation(5,4,i), 3)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }

        //Clear the inside
        for (int i = 1; i < 13; i++) {
            (new FilledHex(getLocation(10,3,i), 2)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }

        for (int i = 1; i < 15; i++) {
            (new FilledHex(getLocation(7,3,i), 2)).iterator().forEachRemaining( (location) -> {
                map.getTile(location).setTerrain(new Ground());
            });
        }
        for (int i = 1; i < 14; i++) {
            (new FilledHex(getLocation(5,4,i), 2)).iterator().forEachRemaining( (location) -> {
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
        map.getTile(getLocation(12,3,12)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,11)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,10)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,9)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,8)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,7)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,6)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,5)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,4)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,3)).setTerrain(new Current(Direction.DOWN));
        map.getTile(getLocation(12,3,2)).setTerrain(new Current(Direction.SOUTH));

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

        column(7,6,2,11,groundMaker);
        column(7,7,2,9,groundMaker);
        column(6,8,2,7,groundMaker);
        column(8,7,2,5,groundMaker);
        column(7,8,2,4,groundMaker);


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
        //map.getTile(getLocation(12,11,1)).setTerrain(new Current(Direction.SOUTH));

        //sand under Lake
        biomePaint = desertLocations;
        filled(16,7,0,7, groundMaker);
        line(14 ,2, 0, Direction.SOUTHWEST, 4, groundMaker);



//        (new HexColumn(getLocation(7,13, 2), 6)).iterator().forEachRemaining( (location) -> {
//            map.getTile(location).setTerrain(new Ground());
//        });

        //Wetlands
        biomePaint = dirtyLocaions;
        (new FilledHex(getLocation(20,10,0), 10)).iterator().forEachRemaining( (location) -> {
            map.getTile(location).setTerrain(new Ground());
        });

        line(23,9,1,Direction.SOUTHWEST, 3, groundMaker);
        line(19,14,1,Direction.SOUTHWEST, 3, groundMaker);
        line(19,15,1,Direction.SOUTHWEST, 2, groundMaker);
        line(23,12,1,Direction.SOUTH, 4, groundMaker);

        line(14,16,1,Direction.NORTHEAST,7,groundMaker);

        point(24,10,1, groundMaker);

        //Marsh River
        biomePaint = dirtyLocaions;
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

        line(20,8,1,Direction.NORTHEAST,3,groundMaker);
        line(22,5,1,Direction.NORTH,5,groundMaker);

        zOffset = 0;
        filled(23,11, 0, 9, groundMaker);
        line(22, 1, 1, Direction.SOUTH, 7, groundMaker);
        line(22, 6, 1, Direction.SOUTHWEST, 3, groundMaker);

        makeFallIsland();

        createDecals(map);

        return map;
    }

    private void createDecals(Map map) {
        //Ramp decals
        map.getTile(6,14,4).add(new Decal("StonePress",-.1,-.2));
        map.getTile(6,13,4).add(new Decal("StonePress", -.1,-.1));
        map.getTile(5,13,4).add(new Decal("StonePress", -.02,.02));
        map.getTile(5,12,5).add(new Decal("StonePress", -.02, .02));
        map.getTile(4,13,5).add(new Decal("StonePress", .02,.02));
        map.getTile(4,12,6).add(new Decal("StonePress", -.02,.02));
        map.getTile(4,11,7).add(new Decal("StonePress", -.02,.02));
        map.getTile(3,11,8).add(new Decal("StonePress", -.02,.02));
        map.getTile(2,11,9).add(new Decal("StonePress", -.02,.02));
        map.getTile(3,10,9).add(new Decal("StonePress", -.02,.02));
        map.getTile(4,9,10).add(new Decal("StonePress", -.02,.02));
        map.getTile(5,8,11).add(new Decal("StonePress", -.02,.02));
        map.getTile(6,7,12).add(new Decal("StonePress", -.02,.02));
        map.getTile(6,6,12).add(new Decal("StonePress", -.02,.02));

        map.getTile(7,6,13).add(new Decal("PlainsTreeLong", -0.1,-0.2));
        map.getTile(7,6,13).add(new Decal("AutumnTreeTall", .1,-.15));
        map.getTile(7,6,13).add(new Decal("PlainsTreeLong", -.3,.1));

        map.getTile(7,7,11).add(new Decal("PlainsTreeLong", -0.1,-0.2));
        map.getTile(7,7,11).add(new Decal("AutumnTreeTall", -.3,.1));
        map.getTile(7,7,11).add(new Decal("AutumnTreeTall", .3,-.15));

        map.getTile(6,8,9).add(new Decal("AutumnTreeTall", -0.1,-0.2));
        map.getTile(6,8,9).add(new Decal("PlainsTreeLong", .1,-.15));
        map.getTile(6,8,9).add(new Decal("AutumnTreeTall", .3,-.15));

        map.getTile(8,7,7).add(new Decal("AutumnTreeTall", .1,-.15));
        map.getTile(8,7,7).add(new Decal("PlainsTreeLong", -.3,.1));
        map.getTile(8,7,7).add(new Decal("AutumnTreeTall", .3,-.15));

        map.getTile(7,8,6).add(new Decal("AutumnTreeTall", -0.1,-0.2));
        map.getTile(7,8,6).add(new Decal("AutumnTreeTall", .1,-.15));
        map.getTile(7,8,6).add(new Decal("PlainsTreeLong", .3,-.15));

        map.getTile(12,25,8).add(new Decal("AutumnTreeTall", -0.1,-0.2));
        map.getTile(12,25,8).add(new Decal("AutumnTreeTall", .1,-.15));
        map.getTile(12,25,8).add(new Decal("PlainsTreeLong", .3,-.15));

        map.getTile(9,31,7).add(new Decal("TundraPineLong",-.3,-.3));
        map.getTile(9,31,7).add(new Decal("TundraPineLong",.025,.025));

        map.getTile(5,19,8).add(new Decal("AutumnTreeTall", -0.1,-0.2));
        map.getTile(5,19,8).add(new Decal("PlainsTreeLong", .1,-.15));
        map.getTile(5,19,8).add(new Decal("AutumnTreeTall", .3,-.15));

        map.getTile(10,4,13).add(new Decal("AutumnTreeTall", .1,-.15));
        map.getTile(10,4,13).add(new Decal("PlainsTreeLong", -.3,.1));
        map.getTile(10,4,13).add(new Decal("AutumnTreeTall", .3,-.15));

        map.getTile(10,5,14).add(new Decal("TallGrass", 0,0));
        map.getTile(10,5,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(9,5,14).add(new Decal("TallGrass", 0,0));
        map.getTile(9,5,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(9,4,14).add(new Decal("TallGrass", 0,0));
        map.getTile(9,4,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(10,5,14).add(new Decal("TallGrass", 0,0));
        map.getTile(10,5,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(7,5,15).add(new Decal("TallGrass", 0,0));
        map.getTile(7,5,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(6,4,15).add(new Decal("TallGrass", 0,0));
        map.getTile(6,4,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(5,4,15).add(new Decal("TallGrass", 0,0));
        map.getTile(5,4,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(5,5,15).add(new Decal("TallGrass", 0,0));
        map.getTile(5,5,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(5,6,15).add(new Decal("TallGrass", 0,0));
        map.getTile(5,6,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(5,4,15).add(new Decal("TallGrass", 0,0));
        map.getTile(5,4,15).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(3,6,14).add(new Decal("TallGrass", 0,0));
        map.getTile(3,6,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(4,6,14).add(new Decal("TallGrass", 0,0));
        map.getTile(4,6,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(4,4,14).add(new Decal("TallGrass", 0,0));
        map.getTile(4,4,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(4,3,14).add(new Decal("TallGrass", 0,0));
        map.getTile(4,3,14).add(new Decal("TallGrass", -.3,-.3));

        map.getTile(6,2,15).add(new Decal("TallGrass", 0,0));
        map.getTile(6,2,15).add(new Decal("TallGrass", -.3,-.3));

        //Flowers
        map.getTile(15,12,2).add(new Decal("Blue Flower", -.1,-.2));
        map.getTile(15,12,2).add(new Decal("White Flower", 0,0));

        map.getTile(16,13,2).add(new Decal("Yellow Flower", -.1,-.2));
        map.getTile(16,13,2).add(new Decal("Blue Flower", 0,0));

        map.getTile(17,12,2).add(new Decal("White Flower", -.1,-.2));
        map.getTile(17,12,2).add(new Decal("Yellow Flower", 0,0));

        map.getTile(18,10,2).add(new Decal("Blue Flower", -.1,-.2));
        map.getTile(18,10,2).add(new Decal("Blue Flower", 0,0));

        map.getTile(14,14,2).add(new Decal("Yellow Flower", -.1,-.2));
        map.getTile(14,14,2).add(new Decal("Yellow Flower", 0,0));

        map.getTile(22,17,2).add(new Decal("White Flower", -.1,-.2));
        map.getTile(22,17,2).add(new Decal("Yellow Flower", 0,0));

        map.getTile(25,12,2).add(new Decal("White Flower", -.1,-.2));
        map.getTile(25,12,2).add(new Decal("Yellow Flower", 0,0));

        //More trees
        map.getTile(17,13,2).add(new Decal("TundraPineLong",0,0));

        map.getTile(15,15,2).add(new Decal("TundraPineLong",-.3,-.3));
        map.getTile(15,15,2).add(new Decal("TundraPineLong",.025,.025));

        map.getTile(18,15,3).add(new Decal("TundraPineLong",-.3,-.3));
        map.getTile(18,15,3).add(new Decal("TundraPineLong",.025,.025));

        map.getTile(21,13,2).add(new Decal("TundraPineLong",-.3,-.3));
        map.getTile(21,13,2).add(new Decal("TundraPineLong",.025,.025));

        map.getTile(23,9,3).add(new Decal("TundraPineLong",-.3,-.3));
        map.getTile(23,9,3).add(new Decal("TundraPineLong",.025,.025));

        map.getTile(23,12,2).add(new Decal("TundraPineLong",0,0));

        map.getTile(25,9,2).add(new Decal("TundraPineLong",0,0));

        map.getTile(22,16,1).add(new Decal("TundraPineLong",0,0));

        //Cacti + bumps
        map.getTile(17,19,1).add(new Decal("DesertBump",0,0));

        map.getTile(16,21,1).add(new Decal("DesertBump",0,0));
        map.getTile(16,21,1).add(new Decal("Cactus1",-.3,-.3));

        map.getTile(14,24,1).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(14,24,1).add(new Decal("Cactus1",0,0));

        map.getTile(14,27,1).add(new Decal("DesertBump",0,0));

        map.getTile(13,29,1).add(new Decal("DesertBump",0,0));
        map.getTile(13,29,1).add(new Decal("Cactus1",-.3,-.3));

        map.getTile(13,27,1).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(13,27,1).add(new Decal("Cactus1",0,0));

        map.getTile(9,27,1).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(9,27,1).add(new Decal("Cactus1",0,0));

        map.getTile(6,32,1).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(6,32,1).add(new Decal("Cactus1",0,0));

        map.getTile(4,34,1).add(new Decal("DesertBump",0,0));

        map.getTile(4,33,1).add(new Decal("DesertBump",0,0));

        map.getTile(0,34,1).add(new Decal("Cactus1",0,0));

        map.getTile(6,26,2).add(new Decal("DesertBump",0,0));

        map.getTile(5,26,2).add(new Decal("DesertBump",0,0));

        map.getTile(5,27,2).add(new Decal("Cactus1",0,0));

        map.getTile(2,25,2).add(new Decal("Cactus1",0,0));

        map.getTile(6,32,1).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(6,32,1).add(new Decal("Cactus1",0,0));

        map.getTile(12,18,3).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(12,18,3).add(new Decal("Cactus1",0,0));

        map.getTile(7,18,4).add(new Decal("Cactus1",-.3,-.3));
        map.getTile(7,18,4).add(new Decal("Cactus1",0,0));
    }

    public Biome createBiomes(SimpleVOFactory factory) {
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
        biomePaint.add(getLocation(r, s, z));
    }

    private Location getLocation(int r, int s, int z) {
        return new Location(r+rOffset, s+sOffset, z+zOffset);
    }

    private void makeFallIsland() {
        biomePaint = fallLocations;
        this.zOffset = 0;
        this.rOffset = 0;
        this.sOffset = 0;

        //base
        filled(28, 28, 3, 7, groundMaker);

        filled(25, 34, 2, 3, groundMaker);
        filled(31, 28, 3, 3, waterMaker);
        filled(31, 28, 2, 3, groundMaker);
        line(32, 25, 3, Direction.NORTH, 3, waterMaker);
        line(32, 25, 2, Direction.NORTH, 3, groundMaker);

        filled(28,25,4,4, groundMaker);

        for(int i = 3; i < 11; ++i){
            map.getTile(new Location(32,23,i)).setTerrain(new Current(Direction.UP));
        }
        map.getTile(new Location(32,23,11)).setTerrain(new Current(Direction.NORTHWEST));
        map.getTile(new Location(31,24,4)).setTerrain(new Water());
        map.getTile(new Location(33,23,3)).setTerrain(new Water());
        filled(33, 23, 2, 2, groundMaker);


        biomePaint = snowLocations;
        line(34, 26, 4, Direction.NORTH, 5, groundMaker);
        for (int i = 5; i < 12; i++) {
            filled(30, 23, i, 2, groundMaker);
            filled(25, 24, i, 2, groundMaker);

            point(27,22,i, groundMaker);
            point(28,22,i, groundMaker);
            point(29,22,i, groundMaker);
        }

        point(27,23,11, groundMaker);
        point(28,23,11, groundMaker);

        line(31,22, 12, Direction.NORTHWEST, 8, groundMaker);

        column(24,26,4,5, groundMaker);
        line(27,22,13, Direction.NORTHWEST, 4, groundMaker);
        column(25,23,12,3, groundMaker);
        column(24,23,12,2, groundMaker);

        column(31,21,13,5, groundMaker);
        column(30,21,13,4, groundMaker);
        column(29,21,13,6, groundMaker);
        column(28,21,13,3, groundMaker);

        column(25,22,14,3, groundMaker);
        column(26,21,14,4, groundMaker);
        column(27,22,14,4, groundMaker);
        column(34,22,5,9, groundMaker);
        column(35,22,3,9, groundMaker);

        for (int i = 4; i < 16; i++) {
            filled(33,21,i,2,groundMaker);
        }


        map.getTile(getLocation(23,28,4)).add(new Obstacle("StoneRock"));
        map.getTile(getLocation(28,28,5)).add(new Obstacle("StoneRock"));
        map.getTile(getLocation(33,24,4)).add(new Obstacle("StoneRock"));
        map.getTile(getLocation(33,21,16)).add(new Obstacle("StoneRock"));


        map.getTile(getLocation(24,27,4)).add(new Decal("AutumnTreeTall", .3, -.5));
        map.getTile(getLocation(22,29,4)).add(new Decal("AutumnTreeTall", -.1, -.15));
        map.getTile(getLocation(22,29,4)).add(new Decal("AutumnTreeTall", .2, -.15));
        map.getTile(getLocation(26,25,5)).add(new Decal("TundraPineLong", .3, -.5));
        map.getTile(getLocation(31,24,5)).add(new Decal("AutumnTreeTall", .3, -.5));
        map.getTile(getLocation(31,31,4)).add(new Decal("AutumnTreeTall", .2, .3));

        map.getTile(getLocation(33,20,16)).add(new Decal("TundraPineLong", .1, -.5));
        map.getTile(getLocation(34,20,16)).add(new Decal("TundraPineLong", .2, -.3));
        map.getTile(getLocation(28,22,13)).add(new Decal("TundraPineLong", .3, -.05));
        map.getTile(getLocation(26,22,14)).add(new Decal("TundraPineLong", -.3, 0));
        map.getTile(getLocation(26,21,18)).add(new Decal("TundraPineLong", .3, -.1));
        map.getTile(getLocation(26,21,18)).add(new Decal("TundraPineLong", -.3, -.3));






    }

    @Override
    public void populateMap(Map map) {
        areasOfEffect(map);
        //items(map);
        mount(map);
        weaponNPC(map);
        dialogNPC(map);
        tradeNPC(map);
        questNPC(map);
        //petNPC(map);
    }

    public void mount(Map map) {
        Mount mount = new Mount(map, Direction.SOUTH);
        mount.setJumpHeight(5);

        map.add(mount, new Location(16,10,2));
    }

    public void petNPC(Map map){
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy(), GameColor.PINK);
        npc.setOccupation(new Pet());
        npc.setCanFallVisitor(new FlyingCanFallVisitor());
        PetSearchingController searchingController = new PetSearchingController(npc, map, 4);
        PetActionController actionController = new PetActionController(npc, map);
        AIController controller = new AIController(searchingController,actionController);
        npc.setController(controller);
        map.add(npc, new Location(9,9,2));
        AITime.getInstance().registerController(controller);
    }

    public void weaponNPC(Map map) {
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy(), new Enemy(), GameColor.GRAY);
        npc.setOccupation(new Enemy());
        EnemySearchingController esc = new EnemySearchingController(npc,map,2);
        EnemyActionController eac = new EnemyActionController(npc,map);
        AIController controller = new AIController(esc,eac);
        npc.setController(controller);
        EquipableItem item = ItemMap.getInstance().getItemAsEquipable("Sword");
        npc.pickup(item);
        npc.equipItem(item);

        npc.pickup(ItemMap.getInstance().getItemAsTakeable("RedBull"));
        map.add(npc, new Location(6,3,16));

        AITime.getInstance().registerController(controller);
    }

    public void dialogNPC(Map map) {
        ArrayList<String> dialog = new ArrayList<>();
        dialog.add("Hello Bubblegum!");
        dialog.add("I'm tickled pink to see you again");
        dialog.add("The grayscalians have taken over...");
        dialog.add("They're North, in the Lemon Chiffon Mountain!");
        dialog.add("If you need anything...");
        dialog.add("*hint hint wink wink*");
        dialog.add("Just call Cadet Blue...");
        NPC npc = new NPC(map, Direction.SOUTHEAST, new DialogInteractionStrategy(dialog), new Friendly(), GameColor.BLUE);

        map.add(npc, new Location(0,30,2));
    }

    public void questNPC(Map map) {
        QuestedItem questItem = new QuestedItem("Diamond of Color", 0);
        QuestableItemReward quest = new QuestableItemReward(questItem, ItemMap.getInstance().getItemAsTakeable("Blaster"), new Location(19, 5, 10));
        ArrayList<String> startQuestDialog = new ArrayList<>();
        startQuestDialog.add("Oh! The hue-manity");
        startQuestDialog.add("The evil grayscalian Monochromia...");
        startQuestDialog.add("...they have stolen our color");
        startQuestDialog.add("Go and fetch the diamond and save Pastelia!");
        startQuestDialog.add("Quick before we fade to grey");

        ArrayList<String> endQuestDialog = new ArrayList<>();
        endQuestDialog.add("Oh! My! Lawd!");
        endQuestDialog.add("You have saved us all!!!");
        endQuestDialog.add("You fill us all with hues and saturation");
        endQuestDialog.add("Here, have this blaster!");

        QuestDialogInteractionStrategy questIter = new QuestDialogInteractionStrategy(startQuestDialog, endQuestDialog, quest);

        NPC npc = new NPC(map, Direction.SOUTHEAST, questIter,new Friendly(),GameColor.PINK);
        map.add(npc, new Location(1, 34, 2));
    }

    public void tradeNPC(Map map) {
        NPC npc = new NPC(map, Direction.SOUTHEAST, new TradeInteractionStrategy(),new Friendly(), GameColor.YELLOW);
        npc.pickup(ItemMap.getInstance().getItemAsEquipable("Sword"));
        npc.pickup(ItemMap.getInstance().getItemAsEquipable("Antenna"));
        npc.pickup(ItemMap.getInstance().getItemAsEquipable("Flare"));
        npc.pickup(ItemMap.getInstance().getItemAsUseable("Spinach"));
        map.add(npc, new Location(3, 26, 3));
    }

    public void areasOfEffect(Map map) {
        TakeDamageAreaOfEffect tkdmgAoe = new TakeDamageAreaOfEffect(1);
        map.add(tkdmgAoe, new Location(3,11,9));

        TeleportAoe teleAoe = new TeleportAoe(new Location(2,34,1));
        map.add(teleAoe, new Location(22, 6, 3));

        HealingAreaOfEffect healAoe = new HealingAreaOfEffect(1);
        map.add(healAoe, new Location(8,1,16));

        LevelUpAoe levelUpAoe = new LevelUpAoe(300);
        map.add(levelUpAoe,new Location(6,11,7));

        InstaDeathAoe deathAoe = new InstaDeathAoe();
        map.add(deathAoe, new Location(3,4,15));
    }

    public void items(Map map) {

        //One shot
        map.add(ItemMap.getInstance().getItemAsOneShot("Orange"), new Location(1,15,3));
        map.add(ItemMap.getInstance().getItemAsOneShot("Blue"), new Location(2,15,3));
        map.add(ItemMap.getInstance().getItemAsOneShot("Purple"), new Location(3,15,3));
        map.add(ItemMap.getInstance().getItemAsOneShot("Red"), new Location(4,15,3));
        map.add(ItemMap.getInstance().getItemAsOneShot("Green"), new Location(5,15,4));

        map.add(ItemMap.getInstance().getItemAsAbility("BindWounds"), new Location(9,13,4));


        map.add(ItemMap.getInstance().getItemAsTakeable("Antenna"), new Location(11, 14,4));
        map.add(ItemMap.getInstance().getItemAsTakeable("Fertilizer"), new Location(11, 13,4));
        map.add(ItemMap.getInstance().getItemAsTakeable("Halo"), new Location(10, 13,4));
       // map.add(ItemMap.getInstance().getItemAsTakeable("Sword"), new Location(9,13,3));

        map.add(ItemMap.getInstance().getItemAsOneShot("Gray"), new Location(2,13,3));

        //Interactive Item??????
        /* TODO implement this */


        //map.add(ItemMap.getInstance().getItemAsAbility("Brawling"), new Location(10,12,3));
        //map.add(ItemMap.getInstance().getItemAsAbility("One-handed weapon"), new Location(5,10,3));



        //Consumeable
    }

    private interface TerrainMaker {
        Terrain makeTerrain();
    }

    private TerrainMaker groundMaker = Ground::new;
    private TerrainMaker waterMaker = Water::new;

}
