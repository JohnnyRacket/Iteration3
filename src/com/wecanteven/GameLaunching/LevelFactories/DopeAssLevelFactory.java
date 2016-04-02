package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

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
                        i + j < 15 ? new Ground() : new Water()
                );
            }
        }
        return map;
    }

    @Override
    public void populateMap() {

    }
}
