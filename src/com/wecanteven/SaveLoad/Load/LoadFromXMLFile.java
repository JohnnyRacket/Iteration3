package com.wecanteven.SaveLoad.Load;

import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.SaveLoad.XMLProcessors.EntityXMLProcessor;
import org.w3c.dom.Node;

import java.io.File;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class LoadFromXMLFile implements LoadGame {

    private LoadGameLauncher launcher;
    private File file;
    private Map map;
    private Avatar avatar;

    public LoadFromXMLFile(LoadGameLauncher launcher, File file) {
        this.launcher = launcher;
        this.file = file;
    }

    @Override
    public Map loadGame() {
        loadSaveFile();
        launcher.loadAvatar(avatar);
        launcher.loadMap(map);
        return null;
    }

    public void loadSaveFile() {


    }








}
