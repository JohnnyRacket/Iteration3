package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.*;
import com.wecanteven.Models.Items.Takeable.*;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;

/**
 * Created by simonnea on 3/31/16.
 */
public interface ItemVisitor {
    // Top Level
    void visitItem(Item item);

    // Second Level
    void visitObstacle(Obstacle obstacle);
    void visitInteractiveItem(InteractiveItem interactable);
    void visitOneShotItem(OneShot oneshot);
    void visitTakeableItem(TakeableItem takeable);

    // Takeable hierarchy
    void visitEquipableItem(EquipableItem equipable);
    void visitUseableItem(UseableItem useable);

    // Useable hierarchy
    void visitAbilityItem(AbilityItem ability);
    void visitStatsModifyItem(StatsModifyUseable consumable);
}
