package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class ItemXMLProcessor extends XMLProcessor {

    public static void formatTakeableItem(TakeableItem i) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("name", i.getName()));
        sf.appendObjectToMostRecent(sf.createSaveElement("Takeable",attr));
    }

    public static TakeableItem parseTakeableItem(Element el) {
        return null;
    }

}
