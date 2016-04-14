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
    private MovableHitBox hitbox;

    public ProjectileHitBoxGenerator(Character caster,StatsAddable effect){
        this.caster = caster;
        hitbox = new MovableHitBox("Range",caster.getLocation(),effect);
        hitbox.setActionHandler(caster.getActionHandler());
    }
    public void generate(){
        Direction direction = caster.getDirection();
        Location destination = hitbox.getLocation().add(direction.getCoords);
        System.out.println("stopped");
    }
}