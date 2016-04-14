package com.wecanteven.Visitors;

import com.wecanteven.Models.Abilities.MovableHitBox;

/**
 * Created by Brandon on 4/13/2016.
 */
public interface MovableHitBoxVisitor {
    public void visitMovableHitBox(MovableHitBox hitbox);

}