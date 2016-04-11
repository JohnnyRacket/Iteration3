package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Brandon on 4/11/2016.
 */
public class HitBox {
    private String name;
    private StatsAddable effect;

    public HitBox(StatsAddable effect){
        this.effect = effect;
    }




    public void interact(Entity entity){
        entity.modifyStats(effect);
    }
}
