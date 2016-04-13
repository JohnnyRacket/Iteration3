package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class InteractionVisitor implements EntityVisitor {

    private Character interactor;

    public InteractionVisitor(Character interactor) {
        this.interactor = interactor;
    }

    @Override
    public void visitEntity(Entity interactee) {
        System.out.println("Characters can't interact with entities");
    }

    @Override
    public void visitCharacter(Character interactee) {
        System.out.println("Characters can't interact with Charactors yet");
        //interactee.interact(interactor);
    }

    @Override
    public void visitNPC(NPC interactee) {
        interactee.interact(interactor);
    }
}