package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class NPC extends Character {
    public NPC(ActionHandler actionHandler, Direction direction){
        super(actionHandler, direction);
    }
    public void interact(){}
}
