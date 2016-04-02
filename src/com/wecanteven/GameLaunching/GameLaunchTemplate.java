package com.wecanteven.GameLaunching;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Map.Map;

/**
 * Created by alexs on 4/1/2016.
 */
public class GameLaunchTemplate {

    private LevelFactory levelFactory = new DopeAssLevelFactory();

    private Map map;
    private Avatar avatar;
    private MainController controller;

    public GameLaunchTemplate(MainController controller){
        this.controller = controller;
    }

    public void launch(){
        System.out.println("launching game");
        createAvatar("test");
        createMap();
        populateMap();
        initializeAreaView();
        initializeUIView();
    }

    protected void createAvatar(String occupation){
        Avatar avatar = new Avatar();


        controller.setAvatar(avatar);
    }

    protected void createMap(){

        levelFactory.createMap();
    }

    protected void populateMap(){
        levelFactory.populateMap();
    }

    protected void initializeAreaView(){

    }

    protected void initializeUIView(){

    }
}
