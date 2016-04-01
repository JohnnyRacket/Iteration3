package com.wecanteven.Models.Items;

import com.wecanteven.Observers.Positionable;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class Item implements Positionable {
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

    /**
     * Visitation rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitItem(this);
    }
}
