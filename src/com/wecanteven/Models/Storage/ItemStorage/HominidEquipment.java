package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Items.Takeable.Equipable.*;

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

    ChestEquipableItem chest;
    BootsEquipableItem boots;
    HeadEquipableItem head;
    WeaponEquipableItem weapon;

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
        if (chest != null) {
            getOwner().addItem(chest);
        }

        chest = item;

        return true;
    }

    @Override
    public boolean equipBoots(BootsEquipableItem item) {
        if (boots != null) {
            getOwner().addItem(boots);
        }

        boots = item;

        return true;
    }

    @Override
    public boolean equipHead(HeadEquipableItem item) {
        if (head != null) {
            getOwner().addItem(head);
        }

        head = item;

        return true;
    }

    @Override
    public boolean equipWeapon(WeaponEquipableItem item) {
        if (weapon != null) {
            getOwner().addItem(weapon);
        }

        weapon = item;

        return true;
    }

    @Override
    public boolean unequipChest(ChestEquipableItem item) {
        chest = null;
        return true;
    }

    @Override
    public boolean unequipBoots(BootsEquipableItem item) {
        boots = null;
        return true;
    }

    @Override
    public boolean unequipHead(HeadEquipableItem item) {
        head = null;
        return true;
    }

    @Override
    public boolean unequipWeapon(WeaponEquipableItem item) {
        weapon = null;
        return true;
    }

    @Override
    public Iterator<EquipableItem> getIterator() {
        List<EquipableItem> equippedItemList = new ArrayList<>();

        if (chest != null)
            equippedItemList.add(chest);
        if (boots != null)
            equippedItemList.add(boots);
        if (head != null)
            equippedItemList.add(head);
        if (weapon != null)
            equippedItemList.add(weapon);

        return equippedItemList.iterator();
    }
}
