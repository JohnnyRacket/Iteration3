package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class AvatarState {
    abstract void attack(Direction d);
    abstract void useAbility(int index);
    abstract boolean equipItem(String id);
    abstract boolean unequipItem(String id);
    abstract boolean equipAbility(String id);
    abstract boolean unequipAbility(String id);
    abstract boolean consume(String id);
    abstract void drop();
    abstract void pickup();
    abstract void interactWith();
    abstract void mount();
    abstract void dismount();
}
