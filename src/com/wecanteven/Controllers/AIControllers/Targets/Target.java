package com.wecanteven.Controllers.AIControllers.Targets;

import com.wecanteven.UtilityClasses.Location;

/**
 * Created by John on 4/5/2016.
 */
public abstract class Target implements TargetVisitable{
    private Location location;
    private int priority;

    public Target(int priority, Location location){
        this.setLocation(location);
        this.setPriority(priority);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
