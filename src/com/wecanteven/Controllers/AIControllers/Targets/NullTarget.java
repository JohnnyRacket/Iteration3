package com.wecanteven.Controllers.AIControllers.Targets;

import com.wecanteven.Visitors.TargetVisitor;

/**
 * Created by John on 4/5/2016.
 */
public class NullTarget extends Target {
    public NullTarget(){
        super(100, null);
    }
    @Override
    public void accept(TargetVisitor visitor) {
        visitor.visitNullTarget(this);
    }
    @Override
    public String toString() {
        return "NullTarget";
    }
}
