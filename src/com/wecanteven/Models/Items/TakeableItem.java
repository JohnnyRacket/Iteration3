package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class TakeableItem extends Item
{
    public TakeableItem(String name) {
        super(name);
    }

    @Override
    public void interact(Character character)
    {
        character.pickup(this);
    }
}
