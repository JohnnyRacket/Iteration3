package com.wecanteven.SaveLoad.Load;

import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.XMLProcessors.EntityXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.TileXMLProcessor;
import com.wecanteven.UtilityClasses.Direction;
import org.w3c.dom.Node;

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
        TileXMLProcessor.setCurrentSave(saveFile);
    }

    @Override
    public Map loadGame() {
        loadSaveFile();
        System.out.println(map.getColumn(0, 1).toString());
        launcher.loadAvatar(getAvatar());
        launcher.loadMap(getMap());
        return null;
    }

    public void loadSaveFile() {
        map = loadMap();
    }

    public Map loadMap() {
        map = TileXMLProcessor.parseMap(saveFile.getElemenetById("Map", 0));
        return map;
    }



    public Avatar getAvatar() {
        return new Avatar(new Character(getMap(), Direction.NORTHEAST), getMap());
    }

    public Map getMap() {
        return map;
    }





}
