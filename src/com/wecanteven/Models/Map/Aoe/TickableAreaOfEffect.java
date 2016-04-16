package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.ModelTime.Tickable;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public abstract class TickableAreaOfEffect extends AreaOfEffect implements Tickable, Alertable {
    private Entity entity;
    private Tile.TileSlot<Entity> observee;
    private boolean active;

    public TickableAreaOfEffect() {
        ModelTime.getInstance().registerTickable(this);
        active = true;
    }

    @Override
    public void tick() {
        if (entity != null && active) {

            apply(entity);
            active = false;
            ModelTime.getInstance().registerAlertable(this, 30);
        }
    }

    @Override
    public void update() {
        if (observee != null) {
            entity = observee.getToken();
        }

        if (entity == null) {

        } else {

        }
    }

    @Override
    public void setObserved(Tile.TileSlot<Entity> observedSlot) {
        observee = observedSlot;
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitTickableAoe(this);
    }

    @Override
    public void alert() {
        active = true;
    }
}
