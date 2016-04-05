package com.wecanteven.SaveLoad.Save;

import com.wecanteven.Models.Map.*;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.SaveVisitors.XMLSaveVisitor;


/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class SaveToXMLFile implements SaveGame {

    private static Map currentMap;
    private SaveFile saveFile;
    private Map map;
    private XMLSaveVisitor saveVisitor;

    public SaveToXMLFile(String fileName) {
        //this.file = getFileFromRes(fileName);
        this.saveFile = new SaveFile(fileName);
        this.map = currentMap;
        this.saveVisitor = new XMLSaveVisitor(saveFile);

    }

    @Override
    public void saveGame() {
        System.out.println("Dispatching the SaveVisitor");
        map.accept(saveVisitor);
        saveFile.writeSaveFile();
    }

    public static void setMap(Map map) {
        currentMap = map;
    }




}
