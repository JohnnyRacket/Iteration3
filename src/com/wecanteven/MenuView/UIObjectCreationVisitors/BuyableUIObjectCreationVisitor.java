package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGridWithCenterTitle;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
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
    private NavigatableGridWithCenterTitle playerMenu;
    private NavigatableGridWithCenterTitle shopMenu;
    private UIViewFactory factory;
    private NPC shopOwner;
    private Character buyer;
    private boolean inPlayerInv = true;

    public BuyableUIObjectCreationVisitor(UIViewFactory factory, NPC shopOwner, Character buyer){
        this.factory = factory;
        this.shopOwner = shopOwner;
        this.buyer = buyer;
    }

    public BuyableUIObjectCreationVisitor(UIViewFactory factory, NPC shopOwner, Character buyer, NavigatableGridWithCenterTitle shopMenu, NavigatableGridWithCenterTitle playerMenu){
        this.factory = factory;
        this.shopOwner = shopOwner;
        this.buyer = buyer;
        this.playerMenu = playerMenu;
        this.shopMenu = shopMenu;
    }

    public NavigatableList getPlayerInvList(){
        return playerInvList;
    }

    public NavigatableList getShopOwnerInvList(){
        return shopOwnerInvList;
    }

    public void visitBoth() {
        playerInvList = new NavigatableList();
        shopOwnerInvList = new NavigatableList();
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

        npc.getItemStorage().accept(this);
    }

    @Override
    public void visitMount(Mount mount) {

    }

    @Override
    public void visitItemStorage(ItemStorage itemStorage) {

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
                factory.createBuyableItemMenu(this, shopOwner, buyer, takeable);
            }));
        }
    }

    @Override
    public void visitTakeaableMoveable(TakeableMoveable takeableMoveable) {
        visitTakeableItem(takeableMoveable.extractItem());
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
    public void visitStatsModifyItem(StatsModifyUseable consumable) {
        visitTakeableItem(consumable);
    }


    public NavigatableGridWithCenterTitle getPlayerMenu() {
        return playerMenu;
    }

    public NavigatableGridWithCenterTitle getShopMenu() {
        return shopMenu;
    }

    public void updateList() {
        getPlayerMenu().setList(getPlayerInvList());
        getPlayerMenu().setTitle("Your Gold: " + buyer.getItemStorage().getMoney().getValue());
        getShopMenu().setList(getShopOwnerInvList());
        getShopMenu().setTitle("Shopkeeper Gold: " + shopOwner.getItemStorage().getMoney().getValue());
    }


}
