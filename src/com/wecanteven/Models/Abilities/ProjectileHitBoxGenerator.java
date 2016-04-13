package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/7/2016.
 */
public class ProjectileHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    private ActionHandler actionHandler;
    private MovableHitBox hitbox;

    public ProjectileHitBoxGenerator(Character caster,StatsAddable effect){
        this.caster = caster;
        actionHandler = caster.getActionHandler();
        hitbox = new MovableHitBox("Range",caster.getLocation(),effect);
    }
    public void generate(){
        Direction direction = caster.getDirection();
        Location destination = hitbox.getLocation().add(direction.getCoords);
        while(actionHandler.move(hitbox,destination,555)){
            if(!hitbox.isActive()){
                System.out.println("moving" + destination);
                destination = destination.add(direction.getCoords);
            }
        }
        System.out.println("stopped");
    }
}