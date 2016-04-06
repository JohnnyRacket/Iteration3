package com.wecanteven.Models.Storage.ItemStorage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.StorageComponent;
import com.wecanteven.Visitors.ItemStorageVisitor;

import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */

public abstract class Inventory extends StorageComponent<ItemStorage> {

    public Inventory(ItemStorage owner) {
        super(owner);
    }

    /**
     * Precondition: Inventory cannot be full
     * */
    public abstract void add(TakeableItem item);

    /**
     * Precondition: Item must be in the inventory
     * */
    public abstract void remove(TakeableItem item);

    public abstract boolean contains(TakeableItem item);

    public abstract boolean isFull();

    public abstract Iterator<TakeableItem> getIterator();

    public void accept(ItemStorageVisitor visitor) {
        visitor.visitInventory(this);

    }
}
