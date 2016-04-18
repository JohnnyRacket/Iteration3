package com.wecanteven.Controllers.AIControllers.SearchingControllers;

import com.wecanteven.Controllers.AIControllers.Targets.EnemyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.FriendlyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.ItemTarget;
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
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Occupation.*;
import com.wecanteven.UtilityClasses.Filled3DHex;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 4/16/2016.
 */
public class PetSearchingController extends AbstractSearchingController {

    public PetSearchingController(Character character, Map map, int searchRadius) {
        super(character, map, searchRadius);
    }
    @Override
    public ArrayList<Location> getSearchArea(){
        Iterator<Location> iter = new Filled3DHex(getCharacter().getLocation(),getSearchRadius(),15).iterator();
        ArrayList<Location> searchArea = new ArrayList<>();
        while(iter.hasNext()){
            searchArea.add(iter.next());
        }
        return searchArea;
    }

    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {
        this.setTarget(c);
        c.getOccupation().accept(this);
    }

    @Override
    public void visitNPC(NPC n) {
        this.setTarget(n);
        n.getOccupation().accept(this);
    }

    @Override
    public void visitMount(Mount mount) {

    }

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitTile(Tile tile) {
        Character character = (Character) tile.getEntity();//this is dangerous
        if(character != null){

            character.accept(this);
        }
        ArrayList<TakeableItem> items = tile.getTakeableItems();
        Iterator<TakeableItem> iter = items.iterator();
        while(iter.hasNext()){
            iter.next().accept(this);
        }
    }

    @Override
    public void visitOccupation(Occupation occupation) {

    }

    @Override
    public void visitEnemy(Enemy enemy) {
        this.addNewTarget(new EnemyTarget(2,this.getTarget().getLocation()));
    }

    @Override
    public void visitFriendly(Friendly friendly) {

    }

    @Override
    public void visitPet(Pet pet) {

    }

    @Override
    public void visitSmasher(Smasher smasher) {
        this.addNewTarget(new FriendlyTarget(3,this.getTarget().getLocation()));
    }

    @Override
    public void visitSneak(Sneak sneak) {
        this.addNewTarget(new FriendlyTarget(3,this.getTarget().getLocation()));
    }

    @Override
    public void visitSummoner(Summoner summoner) {
        this.addNewTarget(new FriendlyTarget(3,this.getTarget().getLocation()));
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
        this.addNewTarget(new ItemTarget(1,takeable.getLocation()));
    }

    @Override
    public void visitTakeaableMoveable(TakeableMoveable takeableMoveable) {
        visitTakeableItem(takeableMoveable.extractItem());
    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {
        this.addNewTarget(new ItemTarget(1,equipable.getLocation()));
    }

    @Override
    public void visitUseableItem(UseableItem useable) {
        this.addNewTarget(new ItemTarget(1,useable.getLocation()));
    }

    @Override
    public void visitAbilityItem(AbilityItem ability) {
        this.addNewTarget(new ItemTarget(1,ability.getLocation()));
    }

    @Override
    public void visitStatsModifyItem(StatsModifyUseable consumable) {
        this.addNewTarget(new ItemTarget(1,consumable.getLocation()));
    }
}
