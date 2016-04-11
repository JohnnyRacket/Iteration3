package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class AvatarState {
    public abstract boolean move(Direction d);
    public abstract void attack();
    public abstract void useAbility(int index);
    public abstract boolean equipItem(String id);
    public abstract boolean unequipItem(String id);
    public abstract boolean equipAbility(String id);
    public abstract boolean unequipAbility(String id);
    public abstract boolean consume(String id);
    public abstract void drop();
    public abstract void pickup();
    public abstract void interactWith();
    public abstract void mount();
    public abstract void dismount();
}
