package com.wecanteven.GameLaunching;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.ViewEngine;

/**
 * Created by alexs on 4/1/2016.
 */
public class GameLaunchTemplate {

    private LevelFactory levelFactory = new DopeAssLevelFactory();

    private Map map;
    private Avatar avatar;
    private MainController controller;
    private ModelEngine modelEngine;
    private ViewEngine viewEngine;

    public GameLaunchTemplate(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine){
        this.controller = controller;
        this.modelEngine = modelEngine;
        this.viewEngine = viewEngine;
    }

    public void launch(){
        System.out.println("launching game");
        createMap();
        createAvatar("test");
        populateMap();
        initializeAreaView();
        initializeUIView();
    }

    protected void createMap(){

        map = levelFactory.createMap();
    }

    protected void createAvatar(String occupation){
        Character player = new Character(map, Direction.SOUTH);
        avatar = new Avatar(player, map);
        map.add(player, new Location(3,2,1));

        //set avatar in things that need it
        controller.setAvatar(avatar);
        UIViewFactory.getInstance().setAvatar(avatar);

    }



    protected void populateMap(){
        levelFactory.populateMap();
    }

    protected void initializeAreaView(){
        viewEngine.clear();
        viewEngine.getManager().popView();
        //viewEngine.registerView(new AreaView(avatar,map));
        viewEngine.registerView(new AreaView(avatar, map));
        controller.setPlayState();
    }

    protected void initializeUIView(){

    }
}
