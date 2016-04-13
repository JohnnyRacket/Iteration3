package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/6/2016.
 */
public class StorageXMLProcessor extends XMLProcessor {


    public static void formatItemStorage(ItemStorage is) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("maxInventoryCapacity", is.getMaxInventoryCapacity()));
        sf.appendObjectToMostRecent(sf.createSaveElement("ItemStorage",attr));
    }

    public static ItemStorage parseItemStorage(Element el) {
        ItemStorage itemStorage = new ItemStorage(sf.getIntAttr(el, "maxInventoryCapacity"));
        //parse items in inventory
        parseInventory(sf.getElemenetById(el, "Inventory", 0), itemStorage);
        //parse items in equipment
        return itemStorage;
    }

    public static void formatInvetory(Inventory i) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("maxInventoryCapacity", i.getMaxCapacity()));
        sf.appendObjectTo("ItemStorage", sf.createSaveElement("Inventory",attr));
    }

    public static void parseInventory(Element el, ItemStorage is) {
        System.out.println("Parsing inventory");
        NodeList itemSlots = sf.getElementsById(el, "ItemSlot");
        for(int i = 0; i < itemSlots.getLength(); ++i){
            //Get item slot number
            int position = sf.getIntAttr((Element)itemSlots.item(i), "position");
            //process item
            TakeableItem item = ItemXMLProcessor.parseTakeableItem((Element)itemSlots.item(i).getChildNodes().item(1));
            System.out.println(item.getName());
            is.addItem(item);
        }

    }

    public static void formatEquipment(Equipment e) {
        ArrayList<Attr> attr = new ArrayList<>();
        sf.appendObjectTo("ItemStorage", sf.createSaveElement("Equipment",attr));
    }

    public static Equipment parseEquipment(Element el, ItemStorage i) {
        return null;
    }

    public static void formatItemSlot(int i) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("position", i));
        sf.appendObjectTo("Inventory", sf.createSaveElement("ItemSlot",attr));
    }
    public static void parseItemSlots(Equipment el) {

    }
}
