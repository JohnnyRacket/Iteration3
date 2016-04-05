package com.wecanteven.Visitors;

import com.wecanteven.Controllers.AIControllers.Targets.*;

/**
 * Created by John on 4/5/2016.
 */
public interface TargetVisitor {
    void visitAvatarTarget(AvatarTarget target);
    void visitItemTarget(ItemTarget target);
    void visitEnemyTarget(EnemyTarget target);
    void visitFriendlyTarget(FriendlyTarget target);
    void visitNullTarget(NullTarget target);
}
