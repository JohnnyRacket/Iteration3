package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.Biomes.DefaultBiome;
import com.wecanteven.AreaView.ViewObjects.Factories.PlainsFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Controllers.AIControllers.ActionControllers.EnemyActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.EnemySearchingController;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Factories.ItemMaps.ItemMap;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.NoInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.OneHandedMeleeWeapon;
import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.*;

import java.util.ArrayList;

/**
 * Created by alexs on 4/14/2016.
 */
public class DemoLevelFactory extends LevelFactory {

    private int rOffset;
    private int sOffset;

    @Override
    public Map createMap() {
        Map map = new Map(40, 40, 20);
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
        map.getTile(getLocation(7,2,14)).setTerrain(new Water());
        map.getTile(getLocation(8,2,14)).setTerrain(new Water());
        map.getTile(getLocation(9,2,14)).setTerrain(new Water());
        map.getTile(getLocation(10,2,12)).setTerrain(new Water());
        map.getTile(getLocation(11,2,12)).setTerrain(new Water());
        map.getTile(getLocation(12,2,12)).setTerrain(new Water());
        map.getTile(getLocation(12,3,12)).setTerrain(new Water());

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


//        (new HexColumn(getLocation(7,13, 2), 6)).iterator().forEachRemaining( (location) -> {
//            map.getTile(location).setTerrain(new Ground());
//        });


        return map;
    }


    private Location getLocation(int r, int s, int z) {
        return new Location(r+rOffset, s+sOffset, z);
    }

    @Override
    public void populateMap(Map map) {


//        areasOfEffect(map);
//        weaponNPC(map);
//        dialogNPC(map);
//        tradeNPC(map);


        areasOfEffect(map);
        items(map);
        weaponNPC(map);
        dialogNPC(map);
        tradeNPC(map);
    }

    @Override
    public Biome createBiomes(ViewObjectFactory factory) {
        return new DefaultBiome(new PlainsFactory(factory));
    }

    public void weaponNPC(Map map) {
        //"Creating an NPC and Giving him a chest Plate
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy());
        OneHandedMeleeWeapon i = new OneHandedMeleeWeapon("Katar", 50, new StatsAddable(0,0,0,0,0,0,0,0,0));
        npc.pickup(i);
        npc.equipItem(i);
        map.add(npc, new Location(7,3,15));
    }

    public void dialogNPC(Map map) {
        ArrayList<String> dialog = new ArrayList<>();
        dialog.add("Hello Avatar");
        dialog.add("You're an idiot and you're playing a dumb game");
        dialog.add("GTFO");
        NPC npc = new NPC(map, Direction.SOUTHWEST, new DialogInteractionStrategy(dialog));
        EnemySearchingController esc = new EnemySearchingController(npc,map,3);
        EnemyActionController eac = new EnemyActionController(npc,map);
        AIController controller = new AIController(esc,eac);
        npc.setController(controller);
        map.add(npc, new Location(9,8,2));
        ModelTime.getInstance().registerTickable(controller);
    }

    public void tradeNPC(Map map) {
        NPC npc = new NPC(map, Direction.SOUTHEAST, new TradeInteractionStrategy());
        npc.pickup(new ChestEquipableItem("Buyable Chestplate", 5, null));
        npc.pickup(new ChestEquipableItem("Buyable Penis", 5, null));
        map.add(npc, new Location(6, 2, 15));
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
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Movement Buff"), new Location(1,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Minor Mana Orb"), new Location(2,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Mana Orb"), new Location(3,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Minor Health Orb"), new Location(4,15,2));
        map.add(ItemMap.getInstance().getItemAsOneShot("Major Health Orb"), new Location(5,15,3));
    }
}
