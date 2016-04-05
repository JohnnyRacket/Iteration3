package com.wecanteven.Controllers.AIControllers.Targets;

import com.wecanteven.Visitors.TargetVisitor;

/**
 * Created by John on 4/5/2016.
 */
public interface TargetVisitable {
    void accept(TargetVisitor visitor);
}
