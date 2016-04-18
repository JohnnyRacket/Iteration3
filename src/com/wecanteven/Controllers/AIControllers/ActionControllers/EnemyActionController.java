package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.DirectionFinder;
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
            if(this.checkLocation(target,2)){
                this.getCharacter().attack(DirectionFinder.getDirection(this.getCharacter().getLocation(),target.getLocation()));
            }else {
                Direction d;
                if((d = getPathToTarget(target)) != null){
                    ModelTime.getInstance().registerAlertable(()->{
                        this.getCharacter().move(d);
                    }, 0);
                }else{
                    //character is unreachable
                }
            }
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
