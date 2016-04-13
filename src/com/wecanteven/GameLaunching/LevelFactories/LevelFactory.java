package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.Models.Map.Map;

/**
 * Created by alexs on 4/1/2016.
 */
public abstract class LevelFactory {
    public abstract Map createMap();
    public abstract void populateMap(Map map);
    public abstract Biome createBiomes(ViewObjectFactory factory);
}
