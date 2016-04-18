package com.wecanteven.Models.Abilities.Effects;

import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Entities.Entity;

/**
 * Created by Brandon on 4/17/2016.
 */
public class BuffEffect implements Effects {
    private Buff effect;
    public BuffEffect(Buff effect){
        this.effect = effect;
    }
    public void interact(Entity entity){
        entity.buff(effect);
    }
}