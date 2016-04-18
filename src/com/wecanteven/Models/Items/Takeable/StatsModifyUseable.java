package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class StatsModifyUseable extends UseableItem {
    private StatsAddable modify;
    public StatsModifyUseable(String name, int value, StatsAddable modify) {
        super(name, value);
        this.modify = modify;
    }

    @Override
    public void use(Character character) {
        character.buff(new Buff(
                "Fly",
                "Purple",
                1000000,
                (entity) -> entity.modifyStatsAdditive(modify),
                (entity) -> entity.modifyStatsSubtractive(modify)
        ));
    }

    /**
     * Visitation Rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitStatsModifyItem(this);
    }
}
