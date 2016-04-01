package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;

/**
 * Created by simonnea on 3/31/16.
 */
public interface ItemVisitor {
    void visitItem(Item item);
    void visitObstacle(Obstacle obstacle);
    void visitInteractableItem(InteractiveItem interactable);
}
