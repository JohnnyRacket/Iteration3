package com.wecanteven.Models.Items.Takeable.Equipable;

import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Visitors.ItemVisitor;
import com.wecanteven.Visitors.WeaponsVisitor;

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

    public EquipableItem(String name, int value, StatsAddable stats) {
        super(name, value);
        this.stats = stats;
    }

    public abstract void equip(Equipment equipment);

    public abstract void unequip(Equipment equipment);

    public StatsAddable getStats() {
        return stats;
    }

    /**
     * Visitation Rights
     * */
    public void accept(ItemVisitor visitor){
        visitor.visitEquipableItem(this);
    }

    public abstract void accept(WeaponsVisitor visitor);
}
