package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/7/2016.
 */
public class ProjectileHitBoxGenerator extends HitBoxGenerator {
    private MovableHitBox hitbox;
    private int distance,speed;

    public ProjectileHitBoxGenerator(String name, Character caster,Effects effect){
        setCaster(caster);
        setMovableHitbox(new MovableHitBox(name,caster.getLocation().adjacent(caster.getDirection()),effect,caster.getActionHandler()));
    }
    @Override
    public void generate(){
        Direction direction = getCaster().getDirection();
        Location destination = getMovableHitbox().getLocation();
        for(int i = 0; i < getDistance(); i++){
            destination = destination.add(direction.getCoords);
        }
        getMovableHitbox().addToMap(getDistance(), getSpeed(), direction);
    }

    public void setMovableHitbox(MovableHitBox hitbox){
        this.hitbox = hitbox;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public MovableHitBox getMovableHitbox(){
        return hitbox;
    }
    public int getDistance(){
        return distance;
    }
    public int getSpeed(){
        return speed;
    }
}