package com.wecanteven.Controllers.AIControllers.SearchingControllers;

import com.wecanteven.Controllers.AIControllers.Targets.Target;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 4/5/2016.
 */
public abstract class AbstractSearchingController implements MapVisitor, EntityVisitor{

    public Target search(){
        return null; //need to think about entity visitor and character states
    }
}
