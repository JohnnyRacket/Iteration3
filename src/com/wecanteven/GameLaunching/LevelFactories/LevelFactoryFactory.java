package com.wecanteven.GameLaunching.LevelFactories;


import java.util.HashMap;

/**
 * Created by Joshua Kegley on 4/13/2016.
 */
public class LevelFactoryFactory {
    private HashMap<String, ILevelFactoryCreateCommand> levelFactories;


    public LevelFactoryFactory() {
        init();
    }

    public void init() {
        levelFactories = new HashMap<>();
        levelFactories.put("DopeAssLevelFactory", ()->{ return new DopeAssLevelFactory(); } );
        levelFactories.put("TSMBlowsLevelFactory", ()->{ return new TSMBlowsLevelFactory(); } );
    }

    public LevelFactory vendLevelFactory(String levelFactoryName) {
        if(levelFactories.containsKey(levelFactoryName)){
            return levelFactories.get(levelFactoryName).create();
        }
        throw new IllegalArgumentException("There is no Level Factory with name: " + levelFactoryName);    }
}
