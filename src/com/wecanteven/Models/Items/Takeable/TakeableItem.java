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
    private int value;
    public TakeableItem(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {return value;}

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