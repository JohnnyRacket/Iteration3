package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class OneShotItemFactory {

    public OneShot vendDefaultOneShot() {
        return new OneShot("Default One Shot", (entity) -> {});
    }

    public OneShot vendInstaDeathOneShot() {
        return new OneShot("Oops you died", (entity) -> entity.die());
    }

    // TODO balancing
    public OneShot vendMinorHealOneShot() {
        return new OneShot("Heal-a-little",
                // TODO semantically add more meaningful messages i.e. entity.increaseHealth( int )
                (entity) -> entity.getStats().addStats(new StatsAddable(0,0,0,0,0,0,0,10,0))
        );
    }

    // TODO balancing
    public OneShot vendManaOrb() {
        return new OneShot ("Here have some mana",
                (entity) -> entity.getStats().addStats(new StatsAddable(0,0,0,0,0,0,0,0,10))
        );
    }
}
