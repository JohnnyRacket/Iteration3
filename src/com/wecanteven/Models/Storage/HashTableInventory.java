package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.*;

/**
 * Created by simonnea on 4/4/16.
 */
public class HashTableInventory extends Inventory {
    private int maxCapacity;
    private int currentCapacity;

    private HashMap<TakeableItem, Integer> inventory;

    public HashTableInventory(Storage owner, int maxCapacity) {
        super(owner);

        this.inventory = new HashMap<>();
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    @Override
    public void add(TakeableItem item) {
        if (isFull()) {
            getOwner().drop(item);
            return;
        }

        if (inventory.containsKey(item)) {
            int newQty = inventory.get(item) + 1;
            inventory.replace(item, newQty);
        }
        else {
            inventory.put(item, 1);
        }

        currentCapacity++;
    }

    @Override
    public void remove(TakeableItem item) {
        if (contains(item)) {
            int newQty = inventory.get(item) - 1;

            if (newQty <= 0)
                inventory.remove(item);
            else
                inventory.replace(item, newQty);

            currentCapacity--;
        }
    }

    @Override
    public boolean contains(TakeableItem item) {
        return inventory.containsKey(item);
    }

    @Override
    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    @Override
    public Iterator<TakeableItem> getIterator() {
        List<TakeableItem> inventoryItems = new ArrayList<>();
        for(Map.Entry<TakeableItem, Integer> e : inventory.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                inventoryItems.add(e.getKey());
            }
        }

        return inventoryItems.iterator();
    }
}
