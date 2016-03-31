package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class InteractiveItem extends Item {

    public InteractiveItem(String name) {
        super(name);
    }

    /**
     * Visitation rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitInteractableItem(this);
    }

    /**
     * Class methods
     * */

    public abstract void interact(Entity entity);

    public abstract void interact(Character character);
}
