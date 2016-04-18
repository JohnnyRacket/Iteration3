package com.wecanteven.GameLaunching.GameLaunchers;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DemoLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Factories.ItemMaps.ItemMap;
import com.wecanteven.Models.Items.Takeable.Equipable.Weapons.FistWeapon;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Pet;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.ViewEngine;

/**
 * Created by alexs on 4/1/2016.
 */
public class NewGameLauncher extends GameLauncher {

    private LevelFactory levelFactory = new DemoLevelFactory();
    //private LevelFactory levelFactory = new DopeAssLevelFactory();
    private Occupation occupation;
    private String face;
    private GameColor playerColor;

    public NewGameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine, Occupation occupation, String face, GameColor color){
        super(controller, modelEngine, viewEngine);
        setLevelFactory(levelFactory);
        this.occupation = occupation;
        playerColor = color;
    }

    /*
        In order for this to work, on the Avatar creation screen that
        creates the NewGameLauncher and then calls createAvatar, passing
        the Occupation
     */

    @Override
    public void launch(){

        createMap();
        createAvatar();
        createPet();
        populateMap(getMap());
        initializeAreaView();
        initializeUIView();
    }

    public void createPet() {
        Character pet = new Character(getMap(), Direction.SOUTH, new Pet(), GameColor.BLUE);
        getMap().add(pet, new Location(16,6,2));
    }

    @Override
    protected void createMap(){
        setMap(getLevelFactory().createMap());
    }

    @Override
    protected void createAvatar(){
        Character player = new Character(getMap(), Direction.SOUTH, occupation, GameColor.GREEN);
        player.setColor(playerColor);
        //DONT INITIALIZE AN EQUIPPABLE THAT INCREASES THE LIVES
        //player.pickup(new HeadEquipableItem("Top Hat", 2, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        //player.pickup(new HeadEquipableItem("THE GAME CRASHER", 1, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        player.pickup(ItemMap.getInstance().getItemAsEquipable("Club"));
        //player.pickup(new DualWieldMeleeWeapon("Katar", 5, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        //player.getItemStorage().equip(new ChestEquipableItem("Mediocre Top", 3, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        setAvatar(new Avatar(player, getMap()));
        getMap().add(player, new Location(16,12,2));

    }

    @Override
    protected void populateMap(Map map){
        getLevelFactory().populateMap(map);
    }

}
