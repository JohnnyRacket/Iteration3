package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.AbilityItem;
import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.ItemStorageVisitor;
import com.wecanteven.Visitors.ItemVisitor;

import java.util.Iterator;

/**
 * Created by John on 4/6/2016.
 */
public class BuyableUIObjectCreationVisitor implements ItemStorageVisitor, ItemVisitor, EntityVisitor, UIObjectCreationVisitor {

    private NavigatableList inventoryItems = new NavigatableList();
    private NavigatableList equippedItems = new NavigatableList();
    private UIViewFactory factory;
    private NPC shopOwner;
    private Character buyer;
    private Character character;
    private boolean inInv = true;

    public BuyableUIObjectCreationVisitor(UIViewFactory factory, NPC shopOwner, Character buyer){
        this.factory = factory;
        this.shopOwner = shopOwner;
        this.buyer = buyer;
    }

    public NavigatableList getInventoryItems (){
        return inventoryItems;
    }

    public NavigatableList getEquippedItems(){
        return equippedItems;
    }

    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {
        this.character = c;
        c.getItemStorage().accept(this);
    }

    @Override
    public void visitNPC(NPC npc) {
        this.character = npc;
        npc.getItemStorage().accept(this);
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
        Iterator<EquipableItem> iter = equipment.getIterator();
        while(iter.hasNext()){
            iter.next().accept(this);
        }
    }

    @Override
    public void visitInventory(Inventory inventory) {
        inInv = true;
        Iterator<TakeableItem> iter = inventory.getIterator();
        while(iter.hasNext()){
            TakeableItem item = iter.next();
            item.accept(this);
            System.out.println(item.getName());
        }
    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {

    }

    @Override
    public void visitInteractiveItem(InteractiveItem interactable) {

    }

    @Override
    public void visitOneShotItem(OneShot oneshot) {

    }

    @Override
    public void visitTakeableItem(TakeableItem takeable) {
        if (character == buyer) {
            inventoryItems.addItem(new GridItem(takeable.getName(), () -> {
                factory.createSellableItemMenu(shopOwner, buyer, takeable);
            }));
        }else {
            inventoryItems.addItem(new GridItem(takeable.getName(), () -> {
                factory.createBuyableItemMenu(shopOwner, buyer, takeable);
            }));
        }
    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {
        visitTakeableItem(equipable);
    }

    @Override
    public void visitUseableItem(UseableItem useable) {
        visitTakeableItem(useable);
    }

    @Override
    public void visitAbilityItem(AbilityItem ability) {
        visitTakeableItem(ability);
    }

    @Override
    public void visitConsumableItem(ConsumeableItem consumable) {
        visitTakeableItem(consumable);
    }
}
