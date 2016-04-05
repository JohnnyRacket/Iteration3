package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.*;

/**
 * Created by simonnea on 4/4/16.
 */
public class HashTableInventory extends Inventory {
    private int maxCapacity;

    private HashSet<TakeableItem> inventory;

    public HashTableInventory(Storage owner, int maxCapacity) {
        super(owner);

        this.inventory = new HashSet<>();
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void add(TakeableItem item) {
        if (isFull()) {
            getOwner().drop(item);
            return;
        }

        inventory.add(item);
    }

    @Override
    public void remove(TakeableItem item) {
        if (contains(item)) {
            inventory.remove(item);
        }
    }

    @Override
    public boolean contains(TakeableItem item) {
        return inventory.contains(item);
    }

    @Override
    public boolean isFull() {
        return inventory.size() == maxCapacity;
    }

    @Override
    public Iterator<TakeableItem> getIterator() {
        return inventory.iterator();
    }
}
