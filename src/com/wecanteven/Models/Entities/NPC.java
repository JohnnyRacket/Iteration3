package com.wecanteven.Models.Entities;

import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.InteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.Iterator;

/**
 * Created by Brandon on 3/31/2016.
 */
public class NPC extends Character {

    private InteractionStrategy interaction;
    private AIController controller;

    public NPC(ActionHandler actionHandler, Direction direction, InteractionStrategy interaction, GameColor color){
        super(actionHandler, direction, color);
        this.interaction = interaction;
        this.interaction.setOwner(this);
    }
    public NPC(ActionHandler actionHandler, Direction direction, InteractionStrategy interaction, Occupation occupation, GameColor color){
        super(actionHandler, direction, occupation, new ItemStorage(25), color);
        this.interaction = interaction;
        this.interaction.setOwner(this);
    }

    public NPC(ActionHandler actionHandler, Direction direction, InteractionStrategy interaction, Occupation occupation, ItemStorage itemStorage, GameColor color){
        super(actionHandler, direction, occupation, itemStorage, color);
        this.interaction = interaction;
        this.interaction.setOwner(this);
    }
    @Override
    public void loseLife(){
        super.loseLife();
        if(controller != null) {
            controller.kill();
        }
        controller = null;
        Iterator<TakeableItem> iter = this.getItemStorage().getInventory().getIterator();
        while(iter.hasNext()){
            this.drop(iter.next());
        }
    }
    @Override
    public void interact(Character c){
        interaction.interact(c);
    }


    public void accept(EntityVisitor visitor) {
        visitor.visitNPC(this);
    }

    public InteractionStrategy getInteraction() {
        return interaction;
    }

    public AIController getController() {
        return controller;
    }

    public void setController(AIController controller) {
        this.controller = controller;
    }
}
