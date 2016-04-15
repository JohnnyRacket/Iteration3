package com.wecanteven.SaveLoad.Load;

import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactoryFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.XMLProcessors.EntityXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.TileXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.XMLProcessor;
import com.wecanteven.UtilityClasses.Direction;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class LoadFromXMLFile implements LoadGame {

    private LoadGameLauncher launcher;
    private SaveFile saveFile;
    private Map map;
    private Avatar avatar;

    public LoadFromXMLFile(LoadGameLauncher launcher, File file) {
        this.launcher = launcher;
        this.saveFile = new SaveFile(file);
        XMLProcessor.setCurrentSave(saveFile);

    }

    @Override
    public void loadGame() {
        loadSaveFile();
        launcher.setLevelFactory(loadLevelFactory());
        launcher.loadMap(getMap());
        launcher.loadAvatar(getAvatar());
        System.out.println("Finished Load");


    }

    public void loadSaveFile() {

        map = loadMap();
        avatar = loadAvatar();

    }

    public Map loadMap() {
        return TileXMLProcessor.parseMap(saveFile.getElemenetById("Map", 0));
    }

    public Avatar loadAvatar() {
        return EntityXMLProcessor.parseAvatar(map, saveFile.getElemenetById("Avatar", 0));
    }

    public LevelFactory loadLevelFactory() {
        return (new LevelFactoryFactory()).vendLevelFactory(saveFile.getElemenetById("Map", 0).getAttribute("name"));
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Map getMap() {
        return map;
    }





}
