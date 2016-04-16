package com.wecanteven.Models.Items;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.ItemVisitor;

import javax.swing.*;

/**
 * Created by simonnea on 3/31/16.
 */

/**
 * DESIGN DEVIATION NOTES
 *
 * The Item hierarchy was revised from the original design because the interact(Entity entity) provided
 * some mixed instance cohesion especially when it came to items that Entities could not interact with (but
 * Characters could).
 * */
public abstract class Item implements Positionable {
    private String name;
    private Location location;

    public Item(String name) {
        this.name = name;
    }

    /**
    * Setters
    * */

    public void setLocation(Location location) {
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
