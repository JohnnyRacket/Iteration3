package com.wecanteven.Models.Factories.ItemFactories.CreateCommands;

import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.StatsModifyUseable;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.CanFallVisitors.FlyingCanFallVisitor;

/**
 * Created by simonnea on 4/17/16.
 */
public class UseableItemFactory {
    public StatsModifyUseable vendStatModifyingBuffItem(String name, int value, StatsAddable modifier) {
        return new StatsModifyUseable(name, value, modifier);
    }

    public UseableItem vendFlyingBuffItem(String name, int value) {
        return new UseableItem(name, value) {
            @Override
            public void use(Character character) {
                character.buff(new Buff(
                        "Fly",
                        "Purple",
                        600,
                        (entity1) -> {
                            entity1.cacheCanFall();
                            entity1.setCanFallVisitor(new FlyingCanFallVisitor());
                        },
                        (entity1) -> {
                            entity1.restoreCanFall();
                            entity1.fall();
                        }));
            }
        };
    }
}
