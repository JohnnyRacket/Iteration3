package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public interface InteractionStrategy {
    void interact(Character c);
    void setOwner(NPC npc);
}
