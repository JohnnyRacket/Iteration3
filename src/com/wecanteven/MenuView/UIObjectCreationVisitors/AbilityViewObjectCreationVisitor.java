package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableListHolder;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.ItemStorageVisitor;

import java.util.Iterator;

/**
 * Created by John on 4/16/2016.
 */
public class AbilityViewObjectCreationVisitor implements EntityVisitor,ItemStorageVisitor {

    private NavigatableList inventoryItems = new NavigatableList();
    private NavigatableList equippedItems = new NavigatableList();
    private UIViewFactory factory;
    private Character character;
    private boolean inInv = true;
    private NavigatableListHolder invHolder;
    private NavigatableListHolder eqHolder;

    public AbilityViewObjectCreationVisitor(UIViewFactory factory, NavigatableListHolder invHolder, NavigatableListHolder eqHolder){
        this.factory = factory;
        this.invHolder = invHolder;
        this.eqHolder = eqHolder;
    }

    public NavigatableList getInventory(){
        return null;
    }
    public NavigatableList getEquipped(){
        return null;
    }

    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {

    }

    @Override
    public void visitNPC(NPC n) {

    }

    @Override
    public void visitMount(Mount mount) {

    }

    @Override
    public void visitItemStorage(ItemStorage itemStorage) {
        inInv = true;
        inventoryItems = new NavigatableList();
        equippedItems = new NavigatableList();
    }

    @Override
    public void visitEquipment(Equipment equipment) {
        inInv = false;
//        Iterator<> iter = equipment.getIterator();
//        while(iter.hasNext()){
//            iter.next().accept(this);
//        }
    }

    @Override
    public void visitInventory(Inventory inventory) {
        inInv = true;
        Iterator<TakeableItem> iter = inventory.getIterator();
        while(iter.hasNext()){
            TakeableItem item = iter.next();
            //item.accept(this);

        }
    }


}
