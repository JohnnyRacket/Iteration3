package com.wecanteven.GameLaunching.GameLaunchers;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.ViewEngine;

/**
 * Created by alexs on 4/1/2016.
 */
public class NewGameLauncher extends GameLauncher {

    private LevelFactory levelFactory = new DopeAssLevelFactory();


    public NewGameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine){
        super(controller, modelEngine, viewEngine);
    }

    /*
        In order for this to work, on the Avatar creation screen that
        creates the NewGameLauncher and then calls createAvatar, passing
        the Occupation
     */

    @Override
    public void launch(){
        System.out.println("launching game");
        createMap();
        createAvatar("test");
        populateMap(getMap());
        initializeAreaView();
        initializeUIView();
    }

    @Override
    protected void createMap(){
        setMap(levelFactory.createMap());
    }

    @Override
    protected void createAvatar(String occupation){
        Character player = new Character(getMap(), Direction.SOUTH);
        player.pickup(new ChestEquipableItem("Dank Chesplate", new StatsAddable(1,1,1,1,1,1,1,1,1)));
        player.pickup(new ChestEquipableItem("Lame Chesplate", new StatsAddable(1,1,1,1,1,1,1,1,1)));
        setAvatar(new Avatar(player, getMap()));
        getMap().add(player, new Location(3,2,1));
        getController().setAvatar(getAvatar());
        UIViewFactory.getInstance().setAvatar(getAvatar());

    }

    @Override
    protected void populateMap(Map map){
        levelFactory.populateMap(map);
    }


}
