package com.wecanteven.SaveLoad.SaveVisitors;

import com.wecanteven.Models.Entities.Avatar;
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
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.XMLProcessors.EntityXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.ItemXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.StorageXMLProcessor;
import com.wecanteven.SaveLoad.XMLProcessors.TileXMLProcessor;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.*;

import java.util.Iterator;


/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class XMLSaveVisitor implements MapVisitor, ColumnVisitor, AvatarVisitor, EntityVisitor, StatsVisitor, ItemStorageVisitor, ItemVisitor{

    SaveFile save;
    //this is hacky as hell - @TODO: Fix this later
    private Avatar avatar;

    public XMLSaveVisitor(SaveFile save) {

        this.save = save;
    }

    @Override
    public void visitMap(Map map) {
        System.out.println("");System.out.println("");System.out.println("");
        System.out.println("Starting Save: ");

        TileXMLProcessor.formatMap(map);
        for(int r = 0; r < map.getrSize(); ++r){
            for(int s = 0; s < map.getsSize(); ++s){
                map.getColumn(r, s).accept(this);
            }
        }
    }

    public void visitColumn(Column column) {
        TileXMLProcessor.formatColumn(column);
        for(int z  = 0; z < column.getZ(); ++z){
            column.getTile(z).accept(this);
        }

    }
    @Override
    public void visitTile(Tile tile) {
        TileXMLProcessor.formatTile(tile);
        if(tile.hasObstacle()) {tile.getObstacle().accept(this);}
        if(tile.hasInteractiveItem()) {tile.getInteractiveItem().accept(this);}
        if(tile.hasOneShot()) {tile.getOneShot().accept(this);}
        if(!tile.getTakeableItems().isEmpty()){
            for (TakeableItem i: tile.getTakeableItems()) {
                i.accept(this);
            }
        }
        if(tile.hasEntity()){tile.getEntity().accept(this);}

    }


    @Override
    public void visitAvatar(Avatar e) {
        avatar = e;
    }

    @Override
    public void visitEntity(Entity e) {
        EntityXMLProcessor.formatEntity(e);
        saveDirection(e.getDirection());
    }

    @Override
    public void visitCharacter(Character e) {
        if(avatar.getCharacter() == e){
            //Save Avatar
            System.out.println("Visiting Avarar: ");

            EntityXMLProcessor.formatAvatar(avatar);
        }else {
            System.out.println("Visiting a non Avatar Char");
            System.out.println(e.getClass());
            EntityXMLProcessor.formatCharacter(e, "Tile");
        }
        saveDirection(e.getDirection());
        e.getStats().accept(this);
        e.getItemStorage().accept(this);
    }

    @Override
    public void visitNPC(NPC npc) {
        System.out.println("Saving NPC: ");
        EntityXMLProcessor.formatNPC(npc, "Tile");
        saveDirection(npc.getDirection());
        npc.getStats().accept(this);
        npc.getItemStorage().accept(this);
    }


    @Override
    public void visitStats(Stats stats) {
        EntityXMLProcessor.formatStats(stats);
    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitItemStorage(ItemStorage itemStorage) {
        StorageXMLProcessor.formatItemStorage(itemStorage);
    }

    @Override
    public void visitEquipment(Equipment equipment) {
        StorageXMLProcessor.formatEquipment(equipment);
        Iterator e = equipment.getIterator();
        EquipableItem i;
        while(e.hasNext()){
            i = (EquipableItem)(e.next());
            System.out.println("Saving Equipped Item: " + i.getName());
            i.accept(this);
        }
    }

    @Override
    public void visitInventory(Inventory inventory) {
        StorageXMLProcessor.formatInvetory(inventory);
        Iterator itemIter = inventory.getOrderedIterator();
        Tuple<TakeableItem, Integer> itemSlot;
        while(itemIter.hasNext()) {
            itemSlot = (Tuple)itemIter.next();
            System.out.println(itemSlot.x.getName());
            StorageXMLProcessor.formatItemSlot(itemSlot.y);
            itemSlot.x.accept(this);
        }
    }

    @Override
    public void visitObstacle(Obstacle item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitOneShotItem(OneShot item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitTakeableItem(TakeableItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitEquipableItem(EquipableItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitUseableItem(UseableItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitAbilityItem(AbilityItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitConsumableItem(ConsumeableItem item) {
        System.out.println("Found Item: " + item.getName());
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    public void saveDirection(Direction direction){
        EntityXMLProcessor.formatDirection(direction);
    }

}

