package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by Brandon on 4/11/2016.
 */
public class EntitySurrogate extends Entity {
    private Character parent;
    public EntitySurrogate(Character parent, ActionHandler actionHandler, Direction d, GameColor color){
        super(actionHandler,d, color);
        this.parent = parent;
    }
}
