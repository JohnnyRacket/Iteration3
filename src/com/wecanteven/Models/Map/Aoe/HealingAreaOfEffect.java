package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by simonnea on 4/11/16.
 */
public class HealingAreaOfEffect extends TickableAreaOfEffect {
    private int perTickHeal;

    public HealingAreaOfEffect(int perTickHeal) {
        super();
        this.perTickHeal = perTickHeal;
    }

    @Override
    public void apply(Entity entity) {
        System.out.println("Entity was healed for " + perTickHeal);
        entity.healDamage(perTickHeal);
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitTickableHealAoe(this);
    }
}
