package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.*;
import com.wecanteven.Visitors.TargetVisitor;

/**
 * Created by John on 4/5/2016.
 */
public abstract class AbstractActionController implements TargetVisitor{
    public void act(Target target){
        target.accept(this);
    }
}
