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
        hitbox = new MovableHitBox("WaterBolt",caster.getLocation(),effect,caster.getActionHandler());
    }
    public void generate(){
        int distance = 3;
        int speed = 30;
        Direction direction = caster.getDirection();
        Location destination = hitbox.getLocation();
        for(int i = 0; i < distance; i++){
            destination = destination.add(direction.getCoords);
        }
        hitbox.addToMap(distance,speed,direction);
    }
}