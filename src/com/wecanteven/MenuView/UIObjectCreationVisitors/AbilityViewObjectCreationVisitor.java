package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.Visitors.ItemStorageVisitor;

/**
 * Created by John on 4/16/2016.
 */
public class AbilityViewObjectCreationVisitor implements ItemStorageVisitor {


    public NavigatableList getInventory(){
        return null;
    }
    public NavigatableList getEquipped(){
        return null;
    }

    @Override
    public void visitItemStorage(ItemStorage itemStorage) {

    }

    @Override
    public void visitEquipment(Equipment equipment) {

    }

    @Override
    public void visitInventory(Inventory inventory) {

    }
}
