package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Stats.StatsAddable;

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
        return new OneShot(name, (entity) -> entity.modifyStats(new StatsAddable(-1,0,0,0,0,0,0,0,0)));
    }

    public OneShot vendMovementSpeedBuffOneShot(String name, int movementSpeedBuff, int duration) {
        return new OneShot(name, (entity) -> entity.buff(new Buff(
                "Red",
                duration,
                (entity1) -> entity1.modifyStats(new StatsAddable(0,0,0,0,0,0,movementSpeedBuff,0,0)),
                (entity1) -> entity1.modifyStats(new StatsAddable(0,0,0,0,0,0,-1 * movementSpeedBuff,0,0))
        )));
    }
}
