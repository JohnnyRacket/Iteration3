package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.UtilityClasses.HexRing;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by John on 4/13/2016.
 */
public class EnemyActionController extends AbstractActionController {

    public EnemyActionController(Character character, Map map) {
        super(character, map);
    }

    @Override
    public void visitItemTarget(ItemTarget target) {

    }

    @Override
    public void visitEnemyTarget(EnemyTarget target) {
        try {
            //System.out.println("moving");
            this.getCharacter().move(getPathToTarget(target));

        }catch (NullPointerException e){
            System.out.println("direction was null, this might mean john is bad a programming");
        }
    }

    @Override
    public void visitFriendlyTarget(FriendlyTarget target) {

    }

    @Override
    public void visitNullTarget(NullTarget target) {

    }
}
