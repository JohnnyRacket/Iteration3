package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/12/2016.
 */
public abstract class OneTimeAreaOfEffect extends AreaOfEffect {
    @Override
    public void setObserved(Tile.TileSlot<Entity> observedSlot) {

    }

    @Override
    public void update() {

    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitOneTimeAoe(this);
    }
}
