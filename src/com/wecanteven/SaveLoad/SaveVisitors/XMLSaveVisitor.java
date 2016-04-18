package com.wecanteven.SaveLoad.SaveVisitors;

import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Occupation.*;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityEquipment;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityInventory;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.XMLProcessors.*;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.*;

import java.util.Iterator;


/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class XMLSaveVisitor implements MapVisitor, ColumnVisitor, AvatarVisitor, EntityVisitor, StatsVisitor, ItemStorageVisitor, ItemVisitor, AreaOfEffectVisitor, OccupationVisitor, AbilityStorageVisitor {

    SaveFile save;
    private Avatar avatar;
    private LevelFactory levelFactory;

    private Location location;

    private String currentParent;

    public XMLSaveVisitor(SaveFile save) {
        //Initizalizing my save location
        location = new Location(0,0,0);
        this.save = save;
    }

    @Override
    public void visitMap(Map map) {


        TileXMLProcessor.formatMap(map);
        for(int r = 0; r < map.getrSize(); ++r){
            for(int s = 0; s < map.getsSize(); ++s){
                location.setR(r);
                location.setS(s);
                map.getColumn(r, s).accept(this);
            }
        }
    }

    @Override
    public void visitColumn(Column column) {
        if(column.isEmpty()){ return; }
        TileXMLProcessor.formatColumn(column);
        for(int z  = 0; z < column.getZ(); ++z){
            location.setZ(z);

            column.getTile(z).accept(this);
        }
    }

    @Override
    public void visitTile(Tile tile) {

        if(!tile.isEmpty()) {
            TileXMLProcessor.formatTile(tile, location);

            //TODO nice to have, change this to grab the item iterator so that we can avoid the conditional logic
            if (tile.hasObstacle()) {
                tile.getObstacle().accept(this);
            }
            if (tile.hasInteractiveItem()) {
                tile.getInteractiveItem().accept(this);
            }
            if (tile.hasOneShot()) {
                tile.getOneShot().accept(this);
            }
            if (!tile.getTakeableItems().isEmpty()) {
                for (TakeableItem i : tile.getTakeableItems()) {
                    i.accept(this);
                }
            }
            if (tile.hasEntity()) {
                tile.getEntity().accept(this);
            }

            Iterator<AreaOfEffect> iter = tile.getAreasOfEffect();

            while (iter.hasNext()) {
                iter.next().accept(this);
            }

        }
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
            EntityXMLProcessor.formatAvatar(avatar);
        }else {
            EntityXMLProcessor.formatCharacter(e, "Tile");
        }
        saveDirection(e.getDirection());
        e.getStats().accept(this);
        e.getItemStorage().accept(this);

        currentParent = "Character";
        e.getOccupation().accept(this);
        e.getAbilityStorage().accept(this);
    }

    @Override
    public void visitNPC(NPC npc) {

        EntityXMLProcessor.formatNPC(npc, "Tile");
        saveDirection(npc.getDirection());
        npc.getStats().accept(this);
        npc.getItemStorage().accept(this);

        currentParent = "NPC";
        npc.getOccupation().accept(this);
        npc.getAbilityStorage().accept(this);
    }

    @Override
    public void visitMount(Mount mount) {

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

            StorageXMLProcessor.formatItemSlot(itemSlot.y);
            itemSlot.x.accept(this);
        }
    }

    @Override
    public void visitObstacle(Obstacle item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {

        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitOneShotItem(OneShot item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitTakeableItem(TakeableItem item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitTakeaableMoveable(TakeableMoveable takeableMoveable) {
        visitTakeableItem(takeableMoveable.extractItem());
    }

    @Override
    public void visitEquipableItem(EquipableItem item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitUseableItem(UseableItem item) {
        if(item.getClass().getSimpleName().equals("")) {
            ItemXMLProcessor.formatItem("UseableItem", item);
            return;
        }
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitAbilityItem(AbilityItem item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    @Override
    public void visitStatsModifyItem(StatsModifyUseable item) {
        ItemXMLProcessor.formatItem(item.getClass().getSimpleName(), item);
    }

    public void saveDirection(Direction direction){
        EntityXMLProcessor.formatDirection(direction);
    }

    @Override
    public void visitAoe(AreaOfEffect aoe) {
        AOEXMLProcessor.formatAoe(aoe);
    }

    @Override
    public void visitTickableAoe(TickableAreaOfEffect aoe) {
        AOEXMLProcessor.formatAoe(aoe);
    }

    @Override
    public void visitOneTimeAoe(OneTimeAreaOfEffect aoe) {
        AOEXMLProcessor.formatAoe(aoe);
    }

    @Override
    public void visitTickableHealAoe(HealingAreaOfEffect aoe) {
        AOEXMLProcessor.formatHealingAreaOfEffect(aoe);
    }

    @Override
    public void visitTickableTakeDamageAoe(TakeDamageAreaOfEffect aoe) {
        AOEXMLProcessor.formatTakeDamageAreaOfEffect(aoe);
    }

    @Override
    public void visitInstaDeathAoe(InstaDeathAoe aoe) {
        AOEXMLProcessor.formatAoe(aoe);
    }

    @Override
    public void visitCoolDownAoe(CoolDownAoE aoe) {
        AOEXMLProcessor.formatCooldownAoe(aoe);
    }

    @Override
    public void visitLevelUpAoe(LevelUpAoe aoe) {
        AOEXMLProcessor.formatCooldownAoe(aoe);
    }

    @Override
    public void visitTeleportAoe(TeleportAoe aoe) {
        AOEXMLProcessor.formatTeleportAoe(aoe);
    }

    public LevelFactory getLevelFactory() {
        return levelFactory;
    }

    public void setLevelFactory(LevelFactory levelFactory) {
        this.levelFactory = levelFactory;
    }

    @Override
    public void visitOccupation(Occupation occupation) {
        OccupationSkillXMLProcessor.formatSkillContainer(currentParent);

        Iterator<Tuple<Skill, Integer>> iter = occupation.getSkillIterator();

        while (iter.hasNext()) {
            Tuple<Skill, Integer> tuple = iter.next();
            OccupationSkillXMLProcessor.formatSkill(tuple);
        }
    }

    @Override
    public void visitEnemy(Enemy enemy) {
        visitOccupation(enemy);
    }

    @Override
    public void visitFriendly(Friendly friendly) {
        visitOccupation(friendly);
    }

    @Override
    public void visitPet(Pet pet) {
        visitOccupation(pet);
    }

    @Override
    public void visitSmasher(Smasher smasher) {
        visitOccupation(smasher);
    }

    @Override
    public void visitSneak(Sneak sneak) {
        visitOccupation(sneak);
    }

    @Override
    public void visitSummoner(Summoner summoner) {
        visitOccupation(summoner);
    }

    @Override
    public void visitAbilityStorage(AbilityStorage itemStorage) {

    }

    @Override
    public void visitAbilityEquiped(AbilityEquipment equipment) {
        EntityXMLProcessor.formatAbilityEquipmentContainer(currentParent);

        Iterator<Ability> iter = equipment.getIterator();

        while (iter.hasNext()) {
            Ability ability = iter.next();
            EntityXMLProcessor.formatAbilities(ability, "AbilityEquipped");
        }
    }

    @Override
    public void visitAbilityInventory(AbilityInventory inventory) {
        EntityXMLProcessor.formatAbilityInventoryContainer(currentParent);

        Iterator<Ability> iter = inventory.getIterator();

        while (iter.hasNext()) {
            Ability ability = iter.next();
            EntityXMLProcessor.formatAbilities(ability, "AbilityInventory");
        }
    }
}

