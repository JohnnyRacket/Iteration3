package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;

/**
 * Created by simonnea on 3/31/16.
 */
public class TakeableItem extends Item
{
    public TakeableItem(String name) {
        super(name);
    }

    public void interact(Character character)
    {
        character.pickup(this);
    }
}