package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by simonnea on 4/13/16.
 */
public class TeleportAoe extends OneTimeAreaOfEffect {
    private Location destination;

    public TeleportAoe(Location destination) {
        super();
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public void apply(Entity entity) {
        entity.move(destination);
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) { visitor.visitTeleportAoe(this); }
}
