package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Aoe.*;

/**
 * Created by simonnea on 4/11/16.
 */
public interface AreaOfEffectVisitor {
    void visitAoe(AreaOfEffect aoe);
    void visitTickableAoe(TickableAreaOfEffect aoe);
    void visitTickableHealAoe(HealingAreaOfEffect aoe);
    void visitTickableTakeDamageAoe(TakeDamageAreaOfEffect aoe);
    void visitOneTimeAoe(OneTimeAreaOfEffect aoe);
    void visitInstaDeathAoe(InstaDeathAoe aoe);
    void visitCoolDownAoe(CoolDownAoE aoe);
    void visitLevelUpAoe(LevelUpAoe aoe);
}
