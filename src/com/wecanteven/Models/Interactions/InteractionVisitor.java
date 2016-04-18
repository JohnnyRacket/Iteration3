package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class InteractionVisitor implements EntityVisitor {

    private Avatar interactor;

    public InteractionVisitor(Avatar interactor) {
        this.interactor = interactor;
    }

    @Override
    public void visitEntity(Entity interactee) {

    }

    @Override
    public void visitCharacter(Character interactee) {
        interactee.interact(interactor);
    }


    @Override
    public void visitNPC(NPC interactee) {
        interactee.interact(interactor.getCharacter());
    }

    @Override
    public void visitMount(Mount mount) {
        mount.interact(interactor);
    }
}
