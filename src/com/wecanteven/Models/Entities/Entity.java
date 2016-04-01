package com.wecanteven.Models.Entities;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Entity {
    protected Location location;
    public Entity(){}
    private boolean move(Direction d){
        return false;
    }
    private void die(){}
    private boolean isActive(){
        return false;
    }
}
