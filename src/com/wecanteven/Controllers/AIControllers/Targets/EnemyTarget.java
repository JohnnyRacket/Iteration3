package com.wecanteven.Controllers.AIControllers.Targets;

import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.TargetVisitor;

/**
 * Created by John on 4/5/2016.
 */
public class EnemyTarget extends Target {
    public EnemyTarget(int priority, Location location) {
        super(priority, location);
    }

    @Override
    public void accept(TargetVisitor visitor) {
        visitor.visitEnemyTarget(this);
    }
}
