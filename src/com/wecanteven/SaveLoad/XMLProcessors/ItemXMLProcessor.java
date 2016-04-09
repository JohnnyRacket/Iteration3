package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Factories.ItemFactories.InteractiveItemFactory;
import com.wecanteven.Models.Factories.ItemFactories.ObstacleItemFactory;
import com.wecanteven.Models.Factories.ItemFactories.OneShotItemFactory;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class ItemXMLProcessor extends XMLProcessor {

    public static void formatItem(String type, Item i) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("name", i.getName()));
        System.out.println("Adding item to most recent: " + type + ":" + i.getName());
        sf.appendObjectToMostRecent(sf.createSaveElement(type, attr));
    }

    public static TakeableItem parseTakeableItem(Element el) {
        return null;
    }
    public static Obstacle parseObstacleItem(Element el) {
        return new ObstacleItemFactory().vendObstacleItem(el.getAttribute("name"));
    }
    public static InteractiveItem parseInteractiveItem(Element el) {
        return new InteractiveItem(el.getAttribute("name"));
    }
    public static OneShot parseOneShotItem(Element el) {
        return new OneShotItemFactory().vendDefaultOneShot(el.getAttribute("name"));
    }


}
