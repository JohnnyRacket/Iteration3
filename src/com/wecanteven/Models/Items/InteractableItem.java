package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;

/**
 * Created by simonnea on 3/31/16.
 */
public class InteractableItem extends Item {

    ItemAction action;

    public InteractableItem(String name, ItemAction quest) {
        super(name);

        this.action = quest;
    }

    @Override
    public void interact(Entity entity) {

    }

    @Override
    public void interact(Character character) {

    }
}
