package com.wecanteven.Models.Storage;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Storage {
    private AbstractInventory inventory;
    private AbstractEquipped equipped;
    public Storage(){}
    private boolean equip(String id){
        return false;
    }
    private boolean unequip(String id){
        return false;
    }
    private boolean use(String id){
        return false;
    }
    private boolean equipArms(String item){  //change String to EquipableItem after the class is created
        return false;
    }
}
