package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Factories.ItemMaps.ItemMap;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.HeadEquipableItem;
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
        sf.appendObjectToMostRecent(sf.createSaveElement(type, attr));
    }

    public static TakeableItem parseTakeableItem(Element el) {
        if(el.getTagName().equals("HeadEquipableItem")){
            System.out.println("Head Equipable Item Found");
            return parseHeadEquipableItem(el);
        }else if(el.getTagName().equals("ChestEquipableItem")){
            System.out.println("Head Equipable Item Found");
            return parseChestEquipableItem(el);
        }
        return new TakeableItem(sf.getStrAttr(el, "name"), 2);
    }

    public static HeadEquipableItem parseHeadEquipableItem(Element el) {
        return (HeadEquipableItem) ItemMap.getInstance().getItemAsEquipable(sf.getStrAttr(el, "name"));
    }

    public static ChestEquipableItem parseChestEquipableItem(Element el) {
        return (ChestEquipableItem) ItemMap.getInstance().getItemAsEquipable(sf.getStrAttr(el, "name"));
    }

    public static Obstacle parseObstacleItem(Element el) {
        return ItemMap.getInstance().getItemAsObstacle(sf.getStrAttr(el, "name"));
    }
    public static InteractiveItem parseInteractiveItem(Element el) {
        return ItemMap.getInstance().getItemAsInteractive(el.getAttribute("name"));
    }
    public static OneShot parseOneShotItem(Element el) {
        return ItemMap.getInstance().getItemAsOneShot(sf.getStrAttr(el, "name"));
    }


}
