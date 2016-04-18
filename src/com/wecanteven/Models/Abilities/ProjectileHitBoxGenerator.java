package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.ViewTime;
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
    private Location location;
    private int traveled;

    public ProjectileHitBoxGenerator(String name, Character caster,Effects effect,int duration){
        setCaster(caster);
        setDistance(duration);
        location = getCaster().getLocation();
        setHitBox(new HitBox(name,caster.getLocation(),effect,caster.getActionHandler(),300));
        traveled = 0;
       // setMovableHitbox(new MovableHitBox(name,caster.getLocation().adjacent(caster.getDirection()),effect,caster.getActionHandler()));
    }
    @Override
    public void generate(){
        move();
//        Direction direction = getCaster().getDirection();
//        Location destination = getMovableHitbox().getLocation();
//        for(int i = 0; i < getDistance(); i++){
//            destination = destination.add(direction.getCoords);
//        }
//        getMovableHitbox().addToMap(getDistance(), getSpeed(), direction);
    }

    private void move(){
        System.out.println("Projectile is on the move");
        if (traveled <= distance) {
            ViewTime.getInstance().register(()->{
                location = location.add(getCaster().getDirection().getCoords);
                getHitBox().addToMap(1, location);
                traveled++;
                move();
            },30);
        }
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