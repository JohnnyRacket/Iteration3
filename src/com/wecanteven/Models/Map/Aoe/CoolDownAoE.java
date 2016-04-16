package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/13/2016.
 */
public abstract class CoolDownAoE extends OneTimeAreaOfEffect implements Alertable {
    private int coolDownTicks;
    private boolean onCooldown;

    public CoolDownAoE(int coolDownTicks, boolean onCooldown) {
        this.coolDownTicks = coolDownTicks;
        this.onCooldown = onCooldown;
    }

    public int getCoolDownTicks() {
        return coolDownTicks;
    }

    @Override
    public void setObserved(Tile.TileSlot<Entity> observedSlot) { }

    @Override
    public void update() { }

    protected abstract void executeEffect(Entity entity);

    @Override
    public void alert() {
        setCooldown(false);

    }

    @Override
    public void apply(Entity entity) {
        if (!onCooldown()) {
            registerCooldown();
            executeEffect(entity);

        } else {

        }
    }

    protected boolean onCooldown() {
        return onCooldown;
    }

    protected void setCooldown(boolean state) {
        onCooldown = state;
    }

    private void registerCooldown() {

        setCooldown(true);
        ModelTime.getInstance().registerAlertable(this, coolDownTicks);
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) { visitor.visitCoolDownAoe(this); }
}
