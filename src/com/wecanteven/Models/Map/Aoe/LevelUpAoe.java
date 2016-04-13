package com.wecanteven.Models.Map.Aoe;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.AreaOfEffectVisitor;

/**
 * Created by Cachorrita on 4/12/2016.
 */
public class LevelUpAoe extends CoolDownAoE {
    public LevelUpAoe(int coolDownTicks, boolean onCooldown) {
        super(coolDownTicks, onCooldown);
    }

    @Override
    protected void executeEffect(Entity entity) {
        entity.levelUp();
        System.out.println("Entity leveled up");
    }

    @Override
    public void accept(AreaOfEffectVisitor visitor) { visitor.visitLevelUpAoe(this); }
}
