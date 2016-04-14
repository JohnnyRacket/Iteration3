package com.wecanteven.Controllers.AIControllers.SearchingControllers;

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
        c.getOccupation();
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
        
    }

    @Override
    public void visitPet(Pet pet) {

    }

    @Override
    public void visitSmasher(Smasher smasher) {

    }

    @Override
    public void visitSneak(Sneak sneak) {

    }

    @Override
    public void visitSummoner(Summoner summoner) {

    }
}
