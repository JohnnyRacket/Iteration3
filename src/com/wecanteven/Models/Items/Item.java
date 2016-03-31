package com.wecanteven.Models.Items;

import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class Item implements Moveable, Positionable {
    private String name;
    private Location location;

    public Item(String name) {
        this.name = name;
    }

    /**
    * Setters
    * */

    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * Getters
     * */

    public String getName() {
        return this.name;
    }

    /**
     * Interface implementations
     * */

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getMovingTicks() {
        return 0;
    }
}
