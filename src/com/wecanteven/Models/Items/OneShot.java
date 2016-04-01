package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Entity;

/**
 * Created by simonnea on 3/31/16.
 */
public class OneShot extends Item {

    private ItemAction action;

    public OneShot(String name, ItemAction action) {
        super(name);

        this.action = action;
    }

    public void interact(Entity entity) {
        action.execute(entity);
    }
}
