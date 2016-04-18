package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Factories.AbilityFactories.AbilityFactory;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class AbilityItem extends UseableItem {
    private static AbilityFactory factory = new AbilityFactory();

    public AbilityItem(String name, int value) {
        super(name, value);
    }

    @Override
    public abstract void use(Character character);

    /**
     * Visitation Rights
     * */

    public void visit(ItemVisitor visitor)
    {
        visitor.visitAbilityItem(this);
    }
}
