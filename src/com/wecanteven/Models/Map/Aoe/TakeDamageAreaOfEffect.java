package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/12/2016.
 */
public class TakeDamageAreaOfEffect extends TickableAreaOfEffect {
    int damagePerTick;

    public TakeDamageAreaOfEffect(int damage) {
        this.damagePerTick = damage;
    }

    public int getDamagePerTick() {
        return damagePerTick;
    }

    @Override
    public void apply(Entity entity) {
        entity.takeDamage(damagePerTick);
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitTickableTakeDamageAoe(this);
    }
}
