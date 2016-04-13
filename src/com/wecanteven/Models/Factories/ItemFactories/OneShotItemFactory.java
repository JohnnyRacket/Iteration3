package com.wecanteven.Models.Factories.ItemFactories;

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
        return new OneShot(name, (entity) -> entity.loseLife());
    }

}
