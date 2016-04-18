package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Observers.Activatable;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public abstract class AvatarState {
    public abstract boolean move(Direction d);
    public abstract boolean jump(Direction d);
    public abstract void attack(Direction dir);
    public abstract void useAbility(int index);
    public abstract boolean equipItem(String id);
    public abstract boolean unequipItem(String id);
    public abstract boolean equipAbility(String id);
    public abstract boolean unequipAbility(String id);
    public abstract boolean consume(String id);
    public abstract void drop();
    public abstract void pickup(TakeableItem item);
    public abstract void interactWith();
    public abstract void mount(Avatar mountee);
    public abstract void dismount();
}
