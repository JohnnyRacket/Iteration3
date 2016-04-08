package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class OneShotItemFactory {

    public OneShot vendDefaultOneShot(String name) {
        return new OneShot(name, (entity) -> {});
    }

    public OneShot vendInstaDeathOneShot(String name) {
        return new OneShot(name, (entity) -> entity.die());
    }

    public OneShot vendHealingOneShot(String name, int amount) {
        return new OneShot(name,
                // TODO semantically add more meaningful messages i.e. entity.increaseHealth( int )
                (entity) -> entity.getStats().addStats(new StatsAddable(0,0,0,0,0,0,0,amount,0))
        );
    }

    public OneShot vendManaRestoringOneShot(String name, int amount) {
        return new OneShot (name,
                (entity) -> entity.getStats().addStats(new StatsAddable(0,0,0,0,0,0,0,0,amount))
        );
    }
}
