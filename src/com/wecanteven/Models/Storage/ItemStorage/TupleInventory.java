package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.UtilityClasses.TwoWayHashMap;

import java.util.*;

/**
 * Created by simonnea on 4/4/16.
 */
public class TupleInventory extends Inventory {
    private int maxCapacity;
    private int numberOfItems;

    private TakeableItem[] inventory;

    public TupleInventory(ItemStorage owner, int maxCapacity) {
        super(owner);

        this.inventory = new TakeableItem[maxCapacity];
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void add(TakeableItem item) {
        if (isFull()) {
            getOwner().drop(item);
            return;
        }

        int newOrder = calculateNextEmptyPosition();

        inventory[newOrder] = item;
        numberOfItems++;
    }

    @Override
    public void add(TakeableItem item, int order) {
        if (inventory[order] == null) {
            inventory[order] = item;
            numberOfItems++;
        }
    }

    @Override
    public void swap(int itemOrderOriginal, int itemOrderDestination) {
        TakeableItem item = inventory[itemOrderOriginal];
        inventory[itemOrderOriginal] = inventory[itemOrderDestination];
        inventory[itemOrderDestination] = item;
    }

    @Override
    public void remove(TakeableItem item) {
        if (contains(item)) {
            for (int i = 0; i < maxCapacity; i++) {
                if (inventory[i] == item) {
                    inventory[i] = null;
                    numberOfItems--;
                }
            }
        }
    }

    @Override
    public TakeableItem remove(int order) {
        TakeableItem item = inventory[order];

        if (item != null) {
            inventory[order] = null;
            numberOfItems--;

            return item;
        }

        return null;
    }

    @Override
    public boolean contains(TakeableItem item) {
        for (int i = 0; i < maxCapacity; i++) {
            if (inventory[i] == item)
                return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return numberOfItems == maxCapacity;
    }

    @Override
    public Iterator<TakeableItem> getIterator() {
        return new ArrayList<TakeableItem>(Arrays.asList(inventory)).iterator();
    }

    @Override
    public Iterator<Tuple<TakeableItem, Integer>> getOrderedIterator() {
        List<Tuple<TakeableItem, Integer>> tupleList = new ArrayList<>();

        for (int i = 0; i < maxCapacity; i++) {
            if (inventory[i] != null) {
                tupleList.add(new Tuple<>(inventory[i], i));
            }
        }

        return tupleList.iterator();
    }

    // Should never return max capacity
    private int calculateNextEmptyPosition()
    {
        for (int i = 0; i < maxCapacity; i++) {
            if (inventory[i] == null)
                return i;
        }

        return maxCapacity;
    }
}
