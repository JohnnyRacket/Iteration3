package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.NoInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.OneHandedMeleeWeapon;
import com.wecanteven.Models.Items.Takeable.Equipable.WeaponEquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Aoe.HealingAreaOfEffect;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;

/**
 * Created by alexs on 4/1/2016.
 */
public class DopeAssLevelFactory extends LevelFactory{

    @Override
    public Map createMap() {
        Map map = new Map(10,10,10);
        for (int i = 0; i<10; i++) {
            for (int j = 0; j<10; j++) {
                map.getTile(i, j, 0).setTerrain(
                        i + j < 15 ? new Ground() : new Current(Direction.NORTHEAST)
                );

            }
        }
        for (int i = 5; i < 9; i++) {
            map.getTile(i, 0, 1).setTerrain(new Ground());
            if (i >6) {
                map.getTile(i, 0, 2).setTerrain(new Ground());
                map.getTile(i, 0, 3).setTerrain(new Ground());
                map.getTile(i, 0, 4).setTerrain(new Ground());
                map.getTile(i, 0, 5).setTerrain(new Ground());
                map.getTile(i, 0, 6).setTerrain(new Ground());


            }
            if (i >6) {
                map.getTile(i, 9, 1).setTerrain(new Current(Direction.NORTH));
                map.getTile(i, 9, 2).setTerrain(new Current(Direction.NORTH));
                //map.getTile(i, 9, 3).setTerrain(new Current(Direction.NORTH));
            }
            if (i >6) {
                map.getTile(i, 8, 1).setTerrain(new Current(Direction.NORTHWEST));
                map.getTile(i, 8, 2).setTerrain(new Current(Direction.NORTHWEST));
               // map.getTile(i, 8, 3).setTerrain(new Current(Direction.NORTHWEST));
            }
                map.getTile(6,9,6).setTerrain(new Ground());
                map.getTile(5,9,4).setTerrain(new Ground());
                map.getTile(4,9,5).setTerrain(new Ground());
                map.getTile(3,9,3).setTerrain(new Ground());
                map.getTile(2,9,2).setTerrain(new Ground());

            map.getTile(4,9,2).setTerrain(new Ground());
            map.getTile(4,9,3).setTerrain(new Ground());
        }
        return map;
    }

    @Override
    public void populateMap(Map map) {

        dialogNPC(map);
        tradeNPC(map);
        weaponNPC(map);


        OneHandedMeleeWeapon katar = new OneHandedMeleeWeapon("Katar", 50, new StatsAddable(1,1,1,1,1,1,1,1,1));
        map.add(katar, new Location(0,0,1));


        map.add(new TakeableItem("NPC Chest", 10), new Location(1, 2, 1));
        map.add(new OneShot("Box", (entity) -> {}), new Location(3,3,1));
        map.add(new InteractiveItem("Button"), new Location(5,5,1));
        map.add(new Obstacle("Box"), new Location(8,2,1));
        //npc.move(Direction.SOUTH);

        HealingAreaOfEffect aoe = new HealingAreaOfEffect(10);
        map.add(aoe, new Location(5,6,1));


    }

    public void weaponNPC(Map map) {
        //"Creating an NPC and Giving him a chest Plate
        NPC npc = new NPC(map, Direction.SOUTH, new NoInteractionStrategy());
        OneHandedMeleeWeapon i = new OneHandedMeleeWeapon("Katar", 50, new StatsAddable(0,0,0,0,0,0,0,0,0));
        npc.pickup(i);
        npc.equipItem(i);
        map.add(npc, new Location(2,2,1));
    }

    public void dialogNPC(Map map) {
        ArrayList<String> dialog = new ArrayList<String>();
        dialog.add("Hello Avatar");
        dialog.add("You're an idiot and you're playing a dumb game");
        dialog.add("GTFO");
        NPC npc = new NPC(map, Direction.SOUTHWEST, new DialogInteractionStrategy(dialog));
        map.add(npc, new Location(2,8,1));

    }

    public void tradeNPC(Map map) {
        NPC npc = new NPC(map, Direction.SOUTHEAST, new TradeInteractionStrategy());
        npc.pickup(new ChestEquipableItem("Buyable Chestplate", 5, null));
        npc.pickup(new ChestEquipableItem("Buyable Penis", 5, null));
        map.add(npc, new Location(4, 5, 1));
    }

}
