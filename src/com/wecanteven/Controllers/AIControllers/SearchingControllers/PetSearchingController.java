package com.wecanteven.Controllers.AIControllers.SearchingControllers;

import com.wecanteven.Controllers.AIControllers.Targets.EnemyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.FriendlyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.ItemTarget;
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
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Occupation.*;

/**
 * Created by John on 4/16/2016.
 */
public class PetSearchingController extends AbstractSearchingController {

    public PetSearchingController(Character character, Map map, int searchRadius) {
        super(character, map, searchRadius);
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
    public void visitMap(Map map) {

    }

    @Override
    public void visitTile(Tile tile) {

    }

    @Override
    public void visitEnemy(Enemy enemy) {
        this.addNewTarget(new EnemyTarget(2,this.getTarget().getLocation()));
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
        this.addNewTarget(new ItemTarget(1,this.getTarget().getLocation()));
    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {

    }

    @Override
    public void visitUseableItem(UseableItem useable) {

    }

    @Override
    public void visitAbilityItem(AbilityItem ability) {

    }

    @Override
    public void visitConsumableItem(ConsumeableItem consumable) {

    }
}
