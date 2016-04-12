package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by simonnea on 4/11/16.
 */

/**
 *
 * DESIGN DEVIATION NOTES
 *
 * */
public abstract class AreaOfEffect implements Observer {
    public abstract void apply(Entity entity);

    public abstract void setObserved(Tile.TileSlot<Entity> observedSlot);

    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitAoe(this);
    }
}
