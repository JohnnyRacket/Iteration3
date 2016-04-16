package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGrid;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableListHolder;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 4/6/2016.
 */
public class EquippableUIObjectCreationVisitor implements ItemStorageVisitor, ItemVisitor, EntityVisitor, UIObjectCreationVisitor {

    private NavigatableList inventoryItems = new NavigatableList();
    private NavigatableList equippedItems = new NavigatableList();
    private UIViewFactory factory;
    private Character character;
    private boolean inInv = true;
    private NavigatableListHolder invHolder;
    private NavigatableListHolder eqHolder;

    public EquippableUIObjectCreationVisitor(UIViewFactory factory){
        this.factory = factory;
    }

    public EquippableUIObjectCreationVisitor(UIViewFactory factory, NavigatableListHolder invHolder, NavigatableListHolder eqHolder){
        this.factory = factory;
        this.invHolder = invHolder;
        this.eqHolder = eqHolder;
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

    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {
        if(inInv) {
            inventoryItems.addItem(new GridItem(equipable.getName(), () -> {

                factory.createEquippableItemMenu(character, invHolder, eqHolder, equipable);
            }));
        }else{
            equippedItems.addItem(new GridItem(equipable.getName(), () -> {

                factory.createEquippedItemMenu(character, invHolder, eqHolder, equipable);
            }));
        }
    }

    @Override
    public void visitUseableItem(UseableItem useable) {
        inventoryItems.addItem(new GridItem(useable.getName(), () ->{
            factory.createUsableItemMenu();
        }));
    }

    @Override
    public void visitAbilityItem(AbilityItem ability) {
        inventoryItems.addItem(new GridItem(ability.getName(), () ->{
            factory.createAbilityItemMenu();
        }));
    }

    @Override
    public void visitConsumableItem(ConsumeableItem consumable) {
        inventoryItems.addItem(new GridItem(consumable.getName(), () ->{
            factory.createConsumableItemMenu();
        }));
    }
}
