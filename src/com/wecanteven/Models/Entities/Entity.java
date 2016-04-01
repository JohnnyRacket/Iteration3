package com.wecanteven.Models.Entities;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Entity {
    private Location location;
    public Entity(){}
    public boolean move(Direction d){
        return false;
    }
    public void die(){}
    public boolean isActive(){
        return false;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
