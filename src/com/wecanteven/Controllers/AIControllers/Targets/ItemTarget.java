package com.wecanteven.Controllers.AIControllers.Targets;

import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.TargetVisitor;

/**
 * Created by John on 4/5/2016.
 */
public class ItemTarget extends Target{

    public ItemTarget(int priority, Location location){
        super(priority, location);
    }
    @Override
    public void accept(TargetVisitor visitor) {
        visitor.visitItemTarget(this);
    }
}
