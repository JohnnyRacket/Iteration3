package com.wecanteven.Models.Entities;

import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 3/31/2016.
 */

public class Entity implements Moveable, Directional{
    private Location location;

    public Entity(){}
    public boolean move(Direction d){
        return false;
    }
    public void die(){}
    public boolean isActive(){
        return false;
    }



    private int movingTicks = 0;
    private Direction direction;

    //TODO: someone write the below shit
    @Override

    public Location getLocation() {
        return location;
    }


    @Override
    public int getMovingTicks() {
        return movingTicks;
    }

    //Alex's testing code
    public void setMovingTicks(int ticks) {
        this.movingTicks = ticks;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
