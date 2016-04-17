package com.wecanteven.Models.Abilities.Effects;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Brandon on 4/16/2016.
 */
public class StatsEffect implements Effects{
    StatsAddable effect;
    public StatsEffect(StatsAddable effect){
        this.effect = effect;
    }
    public void interact(Entity entity){
        entity.modifyStats(effect);
    }
}
