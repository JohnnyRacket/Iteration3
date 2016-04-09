package com.wecanteven.Visitors;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.NPC;

/**
 * Created by simonnea on 3/31/16.
 */
public interface EntityVisitor {
    void visitEntity(Entity e);
    void visitCharacter(Character c);
    void visitNPC(NPC n);
}
