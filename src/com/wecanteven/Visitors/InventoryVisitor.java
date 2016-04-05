package com.wecanteven.Visitors;

import com.wecanteven.Models.Storage.ItemStorage.Inventory;

/**
 * Created by simonnea on 4/1/16.
 */
public interface InventoryVisitor {
    void visitInventory(Inventory inventory);
}
