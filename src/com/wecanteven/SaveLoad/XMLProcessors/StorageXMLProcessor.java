package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/6/2016.
 */
public class StorageXMLProcessor extends XMLProcessor {


    public static void formatItemStorage(ItemStorage is) {
        System.out.println("Formatting Item Storage");
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("maxInventoryCapacity", is.getMaxInventoryCapacity()));
        sf.appendObjectTo("Character", sf.createSaveElement("Character",attr));
    }

    private static ItemStorage parseItemStorage() {
        return null;
    }

    public static void formatInvetory(Inventory i) {
        System.out.println("Formatting Item Inventory");
    }

    private static Inventory parseInventory() {
        return null;
    }

    public static void formatEquipment(Equipment e) {
        System.out.println("Formatting Item Equipment");
    }

    public static Equipment parseEquipment() {
        return null;
    }

}
