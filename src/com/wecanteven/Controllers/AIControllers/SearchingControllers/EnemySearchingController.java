package com.wecanteven.Controllers.AIControllers.SearchingControllers;

import com.wecanteven.Controllers.AIControllers.Targets.EnemyTarget;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Occupation.*;

/**
 * Created by John on 4/13/2016.
 */
public class EnemySearchingController extends AbstractSearchingController {

    public EnemySearchingController(Character character, Map map, int searchRadius) {
        super(character, map, searchRadius);
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
    }

    @Override
    public void visitEnemy(Enemy enemy) {

    }

    @Override
    public void visitPet(Pet pet) {
        this.addNewTarget(new EnemyTarget(2,this.getTarget().getLocation()));
    }

    @Override
    public void visitSmasher(Smasher smasher) {
        this.addNewTarget(new EnemyTarget(1,this.getTarget().getLocation()));
    }

    @Override
    public void visitSneak(Sneak sneak) {
        this.addNewTarget(new EnemyTarget(1,this.getTarget().getLocation()));
    }

    @Override
    public void visitSummoner(Summoner summoner) {
        this.addNewTarget(new EnemyTarget(1,this.getTarget().getLocation()));
    }
}
