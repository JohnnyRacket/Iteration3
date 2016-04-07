package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.StorageComponent;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.ItemStorageVisitor;

import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */

/**
 * DESIGN DEVIATION NOTES
 *
 * Inventory was redesigned to support slot ordering for the user to be able to organize their
 * inventory in an order fashion according to how they want it
 * */

public abstract class Inventory extends StorageComponent<ItemStorage> {

    public Inventory(ItemStorage owner) {
        super(owner);
    }

    /**
     * Precondition: Inventory cannot be full, slot must be empty
     * */
    //Adds to the next empty slot
    public abstract void add(TakeableItem item);

    //Adds to an empty slot in specified order
    public abstract void add(TakeableItem item, int order);

    public abstract void swap(int itemOrderOriginal, int itemOrderDestination);

    /**
     * Precondition: Item must be in the inventory
     * */
    //Removes the first occurence of the item
    public abstract void remove(TakeableItem item);

    /**
     * Precondition: Item must be in the inventory at that slot
     * */
    //Removes the object at a specified order
    public abstract TakeableItem remove(int order);

    public abstract boolean contains(TakeableItem item);

    public abstract boolean isFull();

    public abstract Iterator<TakeableItem> getIterator();

    public abstract Iterator<Tuple<TakeableItem, Integer>> getOrderedIterator();

    public void accept(ItemStorageVisitor visitor) {
        visitor.visitInventory(this);

    }

    public abstract int getMaxCapacity();
}
