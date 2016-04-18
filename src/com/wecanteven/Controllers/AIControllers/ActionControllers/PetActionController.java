package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.EnemyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.FriendlyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.ItemTarget;
import com.wecanteven.Controllers.AIControllers.Targets.NullTarget;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.DirectionFinder;

/**
 * Created by John on 4/16/2016.
 */
public class PetActionController extends AbstractActionController {

    public PetActionController(Character character, Map map) {
        super(character, map);
    }

    @Override
    public void visitItemTarget(ItemTarget target) {
        try {
            System.out.println("moving");
            if(this.getCharacter().getLocation().getZ() > target.getLocation().getZ()){
                ModelTime.getInstance().registerAlertable(()->{
                    this.getCharacter().jump(Direction.DOWN);
                },0);
            }else if(this.getCharacter().getLocation().getZ() < target.getLocation().getZ()){
                ModelTime.getInstance().registerAlertable(()->{
                    this.getCharacter().jump(Direction.UP);
                },0);
            }
            Direction nextDir = getPathToTarget(target);
            ModelTime.getInstance().registerAlertable(()->{
                this.getCharacter().move(nextDir);
            },this.getCharacter().getMovingTicks() + 1);


        }catch (NullPointerException e){
            System.out.println("direction was null, this might mean john is bad a programming");
        }
    }

    @Override
    public void visitEnemyTarget(EnemyTarget target) {
        try {
            if(this.checkAttackLocation(target,2)){
                this.getCharacter().attack(DirectionFinder.getDirection(this.getCharacter().getLocation(),target.getLocation()));
            }else {
                Direction d;
                if((d = getPathToTarget(target)) != null){
                    if(this.getCharacter().getLocation().getZ() > target.getLocation().getZ()){
                        ModelTime.getInstance().registerAlertable(()->{
                            this.getCharacter().jump(Direction.DOWN);
                        },0);
                    }else if(this.getCharacter().getLocation().getZ() < target.getLocation().getZ()){
                        ModelTime.getInstance().registerAlertable(()->{
                            this.getCharacter().jump(Direction.UP);
                        },0);
                    }
                    Direction nextDir = getPathToTarget(target);
                    ModelTime.getInstance().registerAlertable(()->{
                        this.getCharacter().move(nextDir);
                    },this.getCharacter().getMovingTicks() + 1);
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
        System.out.println("this is the loc of entity target: " + target.getLocation());
        try {
            if(this.checkLocation(target,2)){
                //idle
            }else {
                Direction d;
                if((d = getPathToTarget(target)) != null){
                    if(this.getCharacter().getLocation().getZ() > target.getLocation().getZ()){
                        ModelTime.getInstance().registerAlertable(()->{
                            this.getCharacter().jump(Direction.DOWN);
                        },0);
                    }else if(this.getCharacter().getLocation().getZ() < target.getLocation().getZ()){
                        ModelTime.getInstance().registerAlertable(()->{
                            this.getCharacter().jump(Direction.UP);
                        },0);
                    }
                    Direction nextDir = getPathToTarget(target);
                    ModelTime.getInstance().registerAlertable(()->{
                        this.getCharacter().move(nextDir);
                    },this.getCharacter().getMovingTicks() + 1);
                }else{
                    //character is unreachable
                }
            }

        }catch (NullPointerException e){
            System.out.println("direction was null, this might mean john is bad a programming");
        }
    }

    @Override
    public void visitNullTarget(NullTarget target) {

    }
}
