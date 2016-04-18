package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.CanFallVisitors.FlyingCanFallVisitor;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class OneShotItemFactory {

    public OneShot vendOneShot(String name, StatsAddable stats) {
        return new OneShot(name, (entity) -> {
            entity.getStats().addStats(stats);
        });
    }

    public OneShot vendDeathOneShot(String name) {
        return new OneShot(name, (entity) -> entity.modifyStatsAdditive(new StatsAddable(-1,0,0,0,0,0,0,0,0)));
    }

    public OneShot vendMovementSpeedBuffOneShot(String name, int movementSpeedBuff, int duration) {
        return new OneShot(name, (entity) -> entity.buff(new Buff(
                "Speed",
                "Purple",
                duration,
                (entity1) -> entity1.modifyStatsAdditive(new StatsAddable(0,0,0,0,0,0,movementSpeedBuff,0,0)),
                (entity1) -> entity1.modifyStatsSubtractive(new StatsAddable(0,0,0,0,0,0,movementSpeedBuff,0,0))
        )));
    }

    public OneShot vendFlyingBuffOneShot(String name, int duration) {
        return new OneShot(name, (entity) -> entity.buff(new Buff(
                "Fly",
                "Purple",
                duration,
                (entity1) -> {
                    entity1.cacheCanFall();
                    entity1.setCanFallVisitor(new FlyingCanFallVisitor());
                },
                (entity1) -> {
                    entity1.restoreCanFall();
                    entity1.fall();
                }
        )));
    }
}
