package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class UseableItem extends TakeableItem {
    public UseableItem(String name) {
        super(name);
    }

    public abstract void use(Character character);

    /**
     * Visitation Rights
     * */

    public void visit(ItemVisitor visitor) {
        visitor.visitUseableItem(this);
    }
}
