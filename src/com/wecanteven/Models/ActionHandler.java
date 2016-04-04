package com.wecanteven.Models;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by John on 4/2/2016.
 */
public interface ActionHandler {

    boolean move(Entity entity, Location location);
    boolean fall(Entity entity, Location location);
    boolean move(TakeableItem item, Location location);
    boolean fall(TakeableItem item, Location location);
    boolean drop(TakeableItem item, Location location);
}
