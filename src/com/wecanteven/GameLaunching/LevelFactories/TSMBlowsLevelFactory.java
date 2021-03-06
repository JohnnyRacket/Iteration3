package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.Biomes.CustomBiome;
import com.wecanteven.AreaView.Biomes.DefaultBiome;
import com.wecanteven.AreaView.ViewObjects.Factories.*;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.HexLine;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;

/**
 * Created by alexs on 4/12/2016.
 */
public class TSMBlowsLevelFactory extends LevelFactory {

    @Override
    public Map createMap() {
         Map map = new Map(11,11,14);


        //Layer 0
        for (int i = 7; i<11; i++) {
            (new HexLine(new Location(0,i,0), Direction.NORTHEAST,1+i)).iterator().forEachRemaining( (location) ->
            map.getTile(location).setTerrain(new Ground()));
        }
        (new HexLine(new Location(1,10,0), Direction.NORTHEAST,10)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(2,10,0), Direction.NORTHEAST,9)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(4,9,0), Direction.NORTHEAST,7)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));



        //Water
        (new HexLine(new Location(1,8,0), Direction.SOUTHEAST,2)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Water()));
        (new HexLine(new Location(3,8,0), Direction.NORTHEAST,8)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Water()));
        (new HexLine(new Location(7,3,0), Direction.NORTHEAST,4)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Water()));
        (new HexLine(new Location(7,1,0), Direction.NORTHEAST,2)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Water()));
        (new HexLine(new Location(8,1,0), Direction.NORTHEAST,2)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Water()));

        //and this little guy
        map.getTile(8,3,0).setTerrain(new Ground());

        //Layer 1
        (new HexLine(new Location(0,7,1), Direction.NORTHEAST,8)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(0,8,1), Direction.NORTHEAST,7)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(2,7,1), Direction.NORTHEAST,6)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(3,7,1), Direction.NORTHEAST,4)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));

        map.getTile(new Location(0,9,1)).setTerrain(new Ground());
        map.getTile(new Location(6,6,1)).setTerrain(new Ground());

        //Layer 2
        (new HexLine(new Location(0,7,2), Direction.NORTHEAST,8)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(0,8,2), Direction.NORTHEAST,7)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));
        (new HexLine(new Location(3,6,2), Direction.NORTHEAST,3)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));


        //Tundra

        for (int i =3; i < 8; i++) {
            map.getTile(2,5,i).setTerrain(new Ground());
        }
        for (int i =3; i < 11; i++) {
            map.getTile(3,4,i).setTerrain(new Ground());
        }
        for (int i =3; i < 8; i++) {
            map.getTile(4,3,i).setTerrain(new Ground());
        }
        for (int i =3; i < 7; i++) {
            map.getTile(5,2,i).setTerrain(new Ground());
        }
        for (int i =3; i < 7; i++) {
            map.getTile(6,1,i).setTerrain(new Ground());
        }
        for (int i =3; i < 5; i++) {
            map.getTile(3,5,i).setTerrain(new Ground());
        }
        for (int i =3; i < 8; i++) {
            map.getTile(4,4,i).setTerrain(new Ground());
        }
        for (int i =3; i < 5; i++) {
            map.getTile(5,3,i).setTerrain(new Ground());
        }
        for (int i =3; i < 4; i++) {
            map.getTile(6,2,i).setTerrain(new Ground());
        }
        (new HexLine(new Location(7,1,6), Direction.SOUTHEAST,2)).iterator().forEachRemaining( (location) ->
                map.getTile(location).setTerrain(new Ground()));


        //Pillar
        for (int i = 0; i < 7; i++) {
            map.getTile(new Location(9,1,i)).setTerrain(new Ground());
        }


        //Decals
        map.getTile(0,9,2).add(new Decal("AutumnTreeTall", -0.1,-0.2));
        map.getTile(1,9,1).add(new Decal("AutumnTreeTall", 0,0));

        map.getTile(1,10,1).add(new Decal("TallGrassFall", .1, -.3));
        map.getTile(4,9,1).add(new Decal("TallGrassFall", .05, -.3));
        map.getTile(5,7,1).add(new Decal("TallGrassFall", -.4, .2));

        map.getTile(2,9,0).add(new Decal("StonePress", 0,0));
        map.getTile(3,9,0).add(new Decal("StonePress", 0,0));
        map.getTile(4,8,0).add(new Decal("StonePress", 0,0));

        map.getTile(0,10,1).add(new Obstacle("DirtRock"));
        map.getTile(3,7,2).add(new Obstacle("StoneRock"));
        map.getTile(8,5,1).add(new Obstacle("StoneRock"));

        map.getTile(0,8,3).add(new Decal("PlainsTreeLong", -0.15, .2));
        map.getTile(0,7,3).add(new Decal("PlainsTreeLong", 0.15, -.22));
        map.getTile(1,7,3).add(new Decal("PlainsTreeLong", -.11, -.2));
        map.getTile(1,7,3).add(new Decal("PlainsTreeLong", .2, .08));
        map.getTile(2,6,3).add(new Decal("PlainsTreeLong", -0.15, .2));

        map.getTile(0,8,3).add(new Decal("TallGrass",.2,.2));
        map.getTile(1,6,3).add(new Decal("TallGrass",-.2,-.4));
        map.getTile(2,6,3).add(new Decal("TallGrass",.3,-.3));
        map.getTile(3,6,3).add(new Decal("TallGrass",0,-.1));


        map.getTile(5,8,1).add(new Decal("AutumnTreeTall", 0.1, -0.01));
        map.getTile(6,7,1).add(new Decal("AutumnTreeTall", -0.1, -0.25));
        map.getTile(5,7,1).add(new Decal("AutumnTreeTall", 0.03, -0.2));

        map.getTile(10,2,1).add(new Decal("TundraPineLong", -.2,0));
        map.getTile(10,3,1).add(new Decal("TundraPineLong", .1,.2));

        map.getTile(8,4,1).add(new Decal("Cactus1",.09,.2));
        map.getTile(6,3,2).add(new Decal("Cactus1", .3,0));

        map.getTile(2,7,2).add(new Decal("DesertBump",.1,-.1));
        map.getTile(5,5,2).add(new Decal("BigDesertBump", .1, -.15));
        map.getTile(6,4,2).add(new Decal("DesertBump", -.1, -.25));

        map.getTile(9,3,1).add(new Decal("BigDesertBump", -.1, 0));
        map.getTile(8,3,1).add(new Decal("DesertBump", .25, -.1));


        map.getTile(6,1,7).add(new Decal("SnowBump", -.1,.2));

        map.getTile(2,5,8).add(new Decal("TundraPineLong", 0,0));
        map.getTile(3,4,11).add(new Decal("TundraPineLong", 0,-.1));
        map.getTile(5,3,5).add(new Decal("TundraPineLong", .1,-.25));

        map.getTile(9,4,1).add(new Decal("DirtBump", .5, -.5));
        map.getTile(9,4,1).add(new Decal("DirtBump", 0, 0));
        map.getTile(7,6,1).add(new Decal("DirtBump", .2, -.2));

        map.getTile(9,1,7).add(new Decal("TundraPineLong", 0.25, -.25));
        map.getTile(9,1,7).add(new Decal("TundraPineLong", -0.25, .25));

        return map;
    }

    @Override
    public Biome createBiomes(SimpleVOFactory factory) {
        Biome defaultBiome = new DefaultBiome(new PlainsFactory(factory));

        ArrayList<Location> tundraLocations = new ArrayList<>();
        for (int z = 0; z < 14; z++) {
            (new HexLine(new Location(2,5,z), Direction.NORTHEAST,6)).iterator().forEachRemaining( (location) ->
                    tundraLocations.add(location));
            (new HexLine(new Location(4,4,z), Direction.NORTHEAST,4)).iterator().forEachRemaining( (location) ->
                    tundraLocations.add(location));
            tundraLocations.add(new Location(7,1,z));
            tundraLocations.add(new Location(8,1,z));
            tundraLocations.add(new Location(9,1,z));


        }

        Biome tundraBiome = new CustomBiome(new TundraFactory(factory), tundraLocations, defaultBiome);

        ArrayList<Location> dirtyLocations = new ArrayList<>();
        for (int z = 0; z < 14; z++) {
            (new HexLine(new Location(7, 6, z), Direction.NORTHEAST, 4)).iterator().forEachRemaining((location) ->
                    dirtyLocations.add(location));
            dirtyLocations.add(new Location(10,2,z));
        }

        Biome dirtyBiome = new CustomBiome(new DirtFactory(factory), dirtyLocations, tundraBiome);




        ArrayList<Location> desertLocations = new ArrayList<>();
        for (int z = 0; z < 14; z++) {
            (new HexLine(new Location(3,7,z), Direction.NORTHEAST,4)).iterator().forEachRemaining( (location) ->
                    desertLocations.add(location));
            (new HexLine(new Location(5,4,z), Direction.NORTHEAST,3)).iterator().forEachRemaining( (location) ->
                    desertLocations.add(location));
            (new HexLine(new Location(6,6,z), Direction.NORTHEAST,4)).iterator().forEachRemaining( (location) ->
                    desertLocations.add(location));
            desertLocations.add(new Location(2,7,z));
            desertLocations.add(new Location(8,3,z));

        }

        Biome desertBiome = new CustomBiome(new DesertFactory(factory), desertLocations, dirtyBiome);


        ArrayList<Location> autumnLocations = new ArrayList<>();
        for (int z = 0; z < 14; z++) {
            (new HexLine(new Location(0,10,z), Direction.SOUTHEAST,3)).iterator().forEachRemaining( (location) ->
                    autumnLocations.add(location));
            (new HexLine(new Location(0,9,z), Direction.SOUTHEAST,5)).iterator().forEachRemaining( (location) ->
                    autumnLocations.add(location));
            (new HexLine(new Location(4,8,z), Direction.SOUTHEAST,2)).iterator().forEachRemaining( (location) ->
                    autumnLocations.add(location));
            (new HexLine(new Location(5,7,z), Direction.SOUTHEAST,2)).iterator().forEachRemaining( (location) ->
                    autumnLocations.add(location));

        }





        return new CustomBiome(new AutumnFactory(factory), autumnLocations, desertBiome);
    }


    @Override
    public void populateMap(Map map) {

    }
}
