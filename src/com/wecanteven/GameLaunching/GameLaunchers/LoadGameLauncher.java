package com.wecanteven.GameLaunching.GameLaunchers;


import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.TSMBlowsLevelFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.ViewEngine;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class LoadGameLauncher extends GameLauncher {

    public LoadGameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine){
        super(controller, modelEngine, viewEngine);
    }

    @Override
    void populateMap(Map map) {

    }

    @Override
    void createAvatar(String occupation) {

    }

    @Override
    void createMap() {

    }

    @Override
    public void launch(){
        initializeAreaView();
        initializeUIView();

    }

    //Load will call loadMap
    public void loadMap(Map map) {
        setMap(map);
    }

    public void loadAvatar(Avatar avatar){
        setAvatar(avatar);
        getController().setAvatar(avatar);
    }
}
