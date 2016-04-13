package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.ModelTime.Tickable;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public abstract class TickableAreaOfEffect extends AreaOfEffect implements Tickable {
    private Entity entity;
    private Tile.TileSlot<Entity> observee;

    public TickableAreaOfEffect() {
        ModelTime.getInstance().registerTickable(this);
    }

    @Override
    public void tick() {
        if (entity != null) {
            System.out.println("Tick apply");
            apply(entity);
        }
    }

    @Override
    public void update() {
        if (observee != null) {
            entity = observee.getToken();
        }

        if (entity == null) {
            System.out.println("Entity left Aoe");
        } else {
            System.out.println("Entity entered Aoe");
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
}
