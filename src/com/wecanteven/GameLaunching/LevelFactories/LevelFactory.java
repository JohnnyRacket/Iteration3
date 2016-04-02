package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.Models.Map.Map;

/**
 * Created by alexs on 4/1/2016.
 */
public abstract class LevelFactory {
    public abstract Map createMap();
    public abstract void populateMap();
}
