package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class AbilityItem extends UseableItem {


    public AbilityItem(String name) {
        super(name);
    }

    @Override
    public void use(Character character) {
        // Merp derp do shit
    }

    /**
     * Visitation Rights
     * */

    public void visit(ItemVisitor visitor)
    {
        visitor.visitAbilityItem(this);
    }
}
