package com.wecanteven.Models.Entities;

import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Entity implements Moveable, Directional{
    protected Location location;
    public Entity(){}
    private boolean move(Direction d){
        return false;
    }
    private void die(){}
    private boolean isActive(){
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
