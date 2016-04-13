package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/11/2016.
 */
public class HitBox {
    private String name;
    private StatsAddable effect;
    private Location location;

    public HitBox(String name,Location location, StatsAddable effect){
        setName(name);
        setLocation(location);
        setEffect(effect);
    }

    public void interact(Entity entity){
        entity.modifyStats(effect);
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public void setEffect(StatsAddable effect){
        this.effect = effect;
    }

    public String getName(){
        return name;
    }
    public Location getLocation(){
        return location;
    }
    public StatsAddable getEffect(){
        return effect;
    }
}
