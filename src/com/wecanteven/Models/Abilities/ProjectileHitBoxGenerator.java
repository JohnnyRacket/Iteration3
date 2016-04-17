package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
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

    public ProjectileHitBoxGenerator(Character caster,Effects effect){
        this.caster = caster;
        hitbox = new MovableHitBox("WaterBolt",caster.getLocation().adjacent(caster.getDirection()),effect,caster.getActionHandler());
    }
    public void generate(){
        int distance = 5;
        int speed = 30;
        Direction direction = caster.getDirection();
        Location destination = hitbox.getLocation();
        for(int i = 0; i < distance; i++){
            destination = destination.add(direction.getCoords);
        }
        hitbox.addToMap(distance,speed,direction);
    }
}