package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class NoInteractionStrategy implements InteractionStrategy {
    private NPC owner;
    private Character other;

    @Override
    public void interact(Character c) {
        this.other = c;

    }

    @Override
    public void setOwner(NPC npc) {
        this.owner = npc;
    }
}
