package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by simonnea on 4/4/16.
 */
public class HominidEquipment extends Equipment {

    public HominidEquipment(ItemStorage owner) {

        super(owner);
    }

    private HominindEquipmentSlot<ChestEquipableItem> chest = new HominindEquipmentSlot<>();
    private HominindEquipmentSlot<BootsEquipableItem> boots = new HominindEquipmentSlot<>();
    private HominindEquipmentSlot<HeadEquipableItem> head = new HominindEquipmentSlot<>();
    private HominindEquipmentSlot<WeaponEquipableItem> weapon = new HominindEquipmentSlot<>();

    @Override
    public boolean isEquiped(EquipableItem item) {
        Iterator<EquipableItem> iter = getIterator();

        while (iter.hasNext()) {
            if (item == iter.next())
                return true;
        }
        return false;
    }

    @Override
    public boolean equipChest(ChestEquipableItem item) {
        return chest.equip(item);
    }

    @Override
    public boolean equipBoots(BootsEquipableItem item) {
        return boots.equip(item);
    }

    @Override
    public boolean equipHead(HeadEquipableItem item) {
        return head.equip(item);
    }

    @Override
    public boolean equipWeapon(WeaponEquipableItem item) {
        return weapon.equip(item);
    }

    @Override
    public boolean unequipChest(ChestEquipableItem item) {
        return chest.unequip(item);
    }

    @Override
    public boolean unequipBoots(BootsEquipableItem item) {
        return boots.unequip(item);
    }

    @Override
    public boolean unequipHead(HeadEquipableItem item) {
        return head.unequip(item);
    }

    @Override
    public boolean unequipWeapon(WeaponEquipableItem item) {
        return weapon.unequip(item);
    }

    @Override
    public Iterator<EquipableItem> getIterator() {
        List<EquipableItem> equippedItemList = new ArrayList<>();

        chest.addToList(equippedItemList);
        boots.addToList(equippedItemList);
        head.addToList(equippedItemList);
        weapon.addToList(equippedItemList);

        return equippedItemList.iterator();
    }

    public class HominindEquipmentSlot  <T extends EquipableItem> implements Observable{
        private ArrayList<Observer> observers;
        T currentlyEquipped;
        boolean equip(T item) {
            if (currentlyEquipped != null)
                getOwner().addItem(currentlyEquipped);
            currentlyEquipped = item;
            return true;
        }
        boolean unequip(T item) {
            if (currentlyEquipped == item) {
                getOwner().addItem(item);
                currentlyEquipped = null;
                return true;
            }
            return false;
        }

        void addToList(List<EquipableItem> list) {
            if (currentlyEquipped != null)
                list.add(currentlyEquipped);
        }


        @Override
        public ArrayList<Observer> getObservers() {
            return observers;
        }
    }
}
