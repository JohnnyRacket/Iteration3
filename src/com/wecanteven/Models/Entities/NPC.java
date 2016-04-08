package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.InteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class NPC extends Character {

    private InteractionStrategy interaction;

    public NPC(ActionHandler actionHandler, Direction direction, DialogInteractionStrategy interaction){
        super(actionHandler, direction);
        this.interaction = interaction;
    }
    public void interact(Character c){
        interaction.interact(c);
    }
}
