package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Aoe.AreaOfEffect;
import com.wecanteven.Models.Map.Aoe.HealingAreaOfEffect;
import com.wecanteven.Models.Map.Aoe.TickableAreaOfEffect;

/**
 * Created by simonnea on 4/11/16.
 */
public interface AreaOfEffectVisitor {
    void visitAoe(AreaOfEffect aoe);
    void visitTickableAoe(TickableAreaOfEffect aoe);
    void visitTickableHealAoe(HealingAreaOfEffect aoe);
}
