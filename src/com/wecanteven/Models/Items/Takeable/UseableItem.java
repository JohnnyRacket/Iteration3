package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class UseableItem extends TakeableItem {
    public UseableItem(String name, int value) {
        super(name, value);
    }

    public abstract void use(Character character);

    /**
     * Visitation Rights
     * */

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visitUseableItem(this);
    }
}
