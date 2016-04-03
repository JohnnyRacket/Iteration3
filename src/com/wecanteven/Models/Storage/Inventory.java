package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Visitors.InventoryVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class Inventory extends ItemStorage<TakeableItem>
{
    public void accept(InventoryVisitor visitor) {
        visitor.visitInventory(this);
    }
}
