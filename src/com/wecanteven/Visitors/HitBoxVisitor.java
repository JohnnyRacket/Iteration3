package com.wecanteven.Visitors;

import com.wecanteven.Models.Abilities.HitBox;

/**
 * Created by Brandon on 4/13/2016.
 */
public interface HitBoxVisitor {
    public void visitHitBox(HitBox hitbox);

}
