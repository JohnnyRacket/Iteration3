package com.wecanteven.Models.Abilities;

/**
 * Created by Brandon on 4/11/2016.
 */

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Brandon on 4/11/2016.
 */
public class AbilityFactory {
    public Ability vendMeleeAttack(Character caster) {
        MeleeRangeHitBoxGenerator hitBox = new MeleeRangeHitBoxGenerator(caster,new StatsAddable(0,0,0,0,0,0,0,-1,0));
        return new Ability(caster,hitBox);
    }
}