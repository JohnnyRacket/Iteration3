package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class TradeInteractionStrategy implements InteractionStrategy {

    private NPC owner;

    public TradeInteractionStrategy(){
    }

    public void setOwner(NPC npc) {
        this.owner = npc;
    }

    @Override
    public void interact(Character other) {
        //TODO: When Character interacts with this Interactions OWNER
        //TODO: Call createTradeView(owner.getItemStorage(), other.getItemStorage(), other.getSkills.getBargainSkill())
        //UIViewFactory.getInstance().createInventoryView(avatar.getCharacter());
        System.out.println("Starting Trade");
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createTradeView(owner, other, 0);
        },0);
    }
}
