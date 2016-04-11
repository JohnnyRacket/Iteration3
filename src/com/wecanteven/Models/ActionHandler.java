package com.wecanteven.Models;

import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;

/**
 * Created by John on 4/2/2016.
 */
public interface ActionHandler {

    boolean move(Entity entity, Location location, int movespeed);
    boolean fall(Entity entity, Location location);
    boolean move(TakeableItem item, Location location, int movespeed);
    boolean fall(TakeableItem item, Location location);
    boolean drop(TakeableItem item, Location location);
    //void useAbility(ArrayList<Location> locations, StatsAddable effect);
    void death(Entity entity);

    boolean add(HitBox hitBox, Location location);
    boolean remove(HitBox hitBox, Location location);
}
