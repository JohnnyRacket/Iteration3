package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;

/**
 * Created by simonnea on 3/31/16.
 */
public class TakeableItem extends InteractiveItem
{
    public TakeableItem(String name) {
        super(name);
    }

    @Override
    public void interact(Character character)
    {
        character.pickup(this);
    }

    @Override
    public void interact(Entity entity) {
    }
}