package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/12/2016.
 */
public class InstaDeathAoe extends OneTimeAreaOfEffect {
    @Override
    public void apply(Entity entity) {
        System.out.println("Entity lost a life");
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) {
        visitor.visitInstaDeathAoe(this);
    }
}
