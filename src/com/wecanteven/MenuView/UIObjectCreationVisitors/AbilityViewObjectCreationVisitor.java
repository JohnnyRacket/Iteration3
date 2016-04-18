package com.wecanteven.MenuView.UIObjectCreationVisitors;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableListHolder;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityEquipment;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityInventory;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.AbilityStorageVisitor;
import com.wecanteven.Visitors.AbilityVisitor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.Iterator;

/**
 * Created by John on 4/16/2016.
 */
public class AbilityViewObjectCreationVisitor implements EntityVisitor, AbilityStorageVisitor, AbilityVisitor {

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
        return inventoryItems;
    }
    public NavigatableList getEquipped(){
        return equippedItems;
    }

    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {
        this.character = c;
        c.getAbilityStorage().accept(this);
    }

    @Override
    public void visitNPC(NPC n) {
        this.character = n;
        n.getAbilityStorage().accept(this);
    }

    @Override
    public void visitMount(Mount mount) {

    }

    @Override
    public void visitAbilityStorage(AbilityStorage itemStorage) {
        inInv = true;
        inventoryItems = new NavigatableList();
        equippedItems = new NavigatableList();

        for (int i = 0; i < 4; i++) {
            equippedItems.addItem(new GridItem("Empty Slot", () -> {}));
        }
    }

    private int currentSlot;
    @Override
    public void visitAbilityEquiped(AbilityEquipment equipment) {
        inInv = false;
        Iterator<Tuple<Ability,Integer>> iter = equipment.getOrderedIterator();
        while(iter.hasNext()){
            iter.next().x.accept(this);
        }
    }

    @Override
    public void visitAbilityInventory(AbilityInventory inventory) {
        inInv = true;
        Iterator<Ability> iter = inventory.getIterator();
        while(iter.hasNext()){
            Ability ability = iter.next();
            ability.accept(this);
        }
    }

    @Override
    public void visitAbility(Ability ability) {
        if(inInv) {
            inventoryItems.addItem(new GridItem(ability.getName(), () ->
                    factory.createAbilityMenu(character, invHolder, eqHolder, ability)
            ));
        }else{
            equippedItems.addItemToIndex(new GridItem(ability.getName(), () ->
                    factory.createAbilityMenu(character, invHolder, eqHolder, ability)
            ), currentSlot);

        }
    }
}
