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

    private NavigatableList playerInvList = new NavigatableList();
    private NavigatableList shopOwnerInvList = new NavigatableList();
    private UIViewFactory factory;
    private NPC shopOwner;
    private Character buyer;
    private boolean inPlayerInv = true;

    public BuyableUIObjectCreationVisitor(UIViewFactory factory, NPC shopOwner, Character buyer){
        this.factory = factory;
        this.shopOwner = shopOwner;
        this.buyer = buyer;
    }

    public BuyableUIObjectCreationVisitor(UIViewFactory factory, NPC shopOwner, Character buyer, NavigatableList playerInvList, NavigatableList shopOwnerInvList){
        this.factory = factory;
        this.shopOwner = shopOwner;
        this.buyer = buyer;
        this.playerInvList = playerInvList;
        this.shopOwnerInvList = shopOwnerInvList;
    }

    public NavigatableList getPlayerInvList(){
        return playerInvList;
    }

    public NavigatableList getShopOwnerInvList(){
        return shopOwnerInvList;
    }

    public void visitBoth() {
        shopOwner.accept(this);
        buyer.accept(this);
    }

    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {
        c.getItemStorage().accept(this);
    }

    @Override
    public void visitNPC(NPC npc) {
        System.out.println("Visiting an NPC");
        npc.getItemStorage().accept(this);
    }

    @Override
    public void visitItemStorage(ItemStorage itemStorage) {
        inPlayerInv = true;
        playerInvList = new NavigatableList();
        shopOwnerInvList = new NavigatableList();
    }

    @Override
    public void visitEquipment(Equipment equipment) {
    }

    @Override
    public void visitInventory(Inventory inventory) {
        if(buyer.getItemStorage().getInventory() == inventory) {
            inPlayerInv = true;
        } else{
            inPlayerInv = false;
        }
        Iterator<TakeableItem> iter = inventory.getIterator();
        while (iter.hasNext()) {
            TakeableItem item = iter.next();
            item.accept(this);
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
        if (inPlayerInv) {
            playerInvList.addItem(new GridItem(takeable.getName(), () -> {
                factory.createSellableItemMenu(shopOwner, buyer, takeable);
            }));
        }else {
            shopOwnerInvList.addItem(new GridItem(takeable.getName(), () -> {
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

    public void setPlayerInvList(NavigatableList playerInvList) {
        this.playerInvList = playerInvList;
    }

    public void setShopOwnerInvList(NavigatableList shopOwnerInvList) {
        this.shopOwnerInvList = shopOwnerInvList;
    }
}
