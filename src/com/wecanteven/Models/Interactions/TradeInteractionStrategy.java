package com.wecanteven.Models.Interactions;

import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class TradeInteractionStrategy implements InteractionStrategy {

    private Character owner;

    public TradeInteractionStrategy(Character c){
        this.owner = c;
    }
    @Override
    public void interact(Character other) {
        //TODO: When Character interacts with this Interactions OWNER
        //TODO: Call createTradeView(owner.getItemStorage(), other.getItemStorage(), other.getSkills.getBargainSkill())
        //UIViewFactory.getInstance().createInventoryView(avatar.getCharacter());

    }
}
