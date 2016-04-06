package com.wecanteven.Visitors;

import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;

/**
 * Created by simonnea on 4/1/16.
 */
public interface ItemStorageVisitor {
    void visitItemStorage(ItemStorage itemStorage);
    void visitEquipment(Equipment equipment);
    void visitInventory(Inventory inventory);
}
