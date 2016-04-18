package com.wecanteven.Visitors;

import com.wecanteven.Models.Abilities.Ability;

/**
 * Created by simonnea on 4/17/16.
 */
public interface AbilityVisitor {
    void visitAbility(Ability ability);
}
