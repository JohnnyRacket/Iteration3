package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class TakeableItem extends Item
{
    public TakeableItem(String name) {
        super(name);
    }

    public void interact(Character character) {
        character.pickup(this);
    }

    /**
     * Visitation Rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitTakeableItem(this);
    }
}