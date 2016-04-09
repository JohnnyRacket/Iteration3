package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.InteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class NPC extends Character {

    private InteractionStrategy interaction;

    public NPC(ActionHandler actionHandler, Direction direction, DialogInteractionStrategy interaction){
        super(actionHandler, direction);
        this.interaction = interaction;
    }

    public NPC(ActionHandler actionHandler, Direction direction, DialogInteractionStrategy interaction, Occupation occupation, ItemStorage itemStorage){
        super(actionHandler, direction, occupation, itemStorage);
        this.interaction = interaction;
    }

    public void interact(Character c){
        interaction.interact(c);
    }

    public void accept(EntityVisitor visitor) {
        System.out.println("Visiting NPC");
        visitor.visitNPC(this);
    }
}
