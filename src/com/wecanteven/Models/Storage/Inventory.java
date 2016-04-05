package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Visitors.InventoryVisitor;

import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */

public abstract class Inventory extends StorageComponent {

    public Inventory(Storage owner) {
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

    public void accept(InventoryVisitor visitor) {
        visitor.visitInventory(this);
    }
}
