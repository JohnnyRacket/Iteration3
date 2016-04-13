package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/12/2016.
 */
public class TakeDamageAreaOfEffect extends TickableAreaOfEffect {
    @Override
    public void apply(Entity entity) {

    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitTickableTakeDamageAoe(this);
    }
}
