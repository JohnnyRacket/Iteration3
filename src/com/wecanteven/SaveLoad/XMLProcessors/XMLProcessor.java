package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.SaveLoad.SaveFile;

/**
 * Created by Joshua Kegley on 4/6/2016.
 */
public abstract class XMLProcessor {

    protected static  SaveFile sf;

    public static void setCurrentSave(SaveFile currentSave) {
        EntityXMLProcessor.sf = currentSave;
    }
}
