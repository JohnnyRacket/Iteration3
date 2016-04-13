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

    @Override
    public void setObserved(Tile.TileSlot<Entity> observedSlot) { }

    @Override
    public void update() { }

    protected abstract void executeEffect(Entity entity);

    @Override
    public void alert() {
        setCooldown(false);
        System.out.println("Cooldown Expired");
    }

    @Override
    public void apply(Entity entity) {
        if (!onCooldown()) {
            registerCooldown();
            executeEffect(entity);
            System.out.println("Aoe is active");
        } else {
            System.out.println("Aoe is on cooldown");
        }
    }

    protected boolean onCooldown() {
        return onCooldown;
    }

    protected void setCooldown(boolean state) {
        onCooldown = state;
    }

    private void registerCooldown() {
        System.out.println("Cooldown started");
        setCooldown(true);
        ModelTime.getInstance().registerAlertable(this, coolDownTicks);
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) { visitor.visitCoolDownAoe(this); }
}
