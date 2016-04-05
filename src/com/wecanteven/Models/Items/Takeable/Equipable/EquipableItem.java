package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.Equipment;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */

/**
 * DESIGN DEVIATION NOTES
 *
 * The EquipableItem hierarchy was extend to allow equipable items to self determine their equipment slots.
 * This was done to avoid having Equipable Item type checking to know what item could go where. Yay for eliminating
 * conditional logic!
 * */
public abstract class EquipableItem extends TakeableItem {

    private StatsAddable stats;

    public EquipableItem(String name, StatsAddable stats) {
        super(name);

        this.stats = stats;
    }

    public abstract void equip(Equipment equipment);

    public abstract void unequip(Equipment equipment);

    /**
     * Visitation Rights
     * */
    public void visit(ItemVisitor visitor)
    {
        visitor.visitEquipableItem(this);
    }
}
