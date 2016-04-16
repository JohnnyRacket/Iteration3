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
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(caster,new StatsAddable(0,0,0,0,0,0,0,-1,0));
        return new Ability(caster,generator);
    }
    public Ability vendRangedAttack(Character caster) {
        ProjectileHitBoxGenerator generator = new ProjectileHitBoxGenerator(caster,new StatsAddable(0,0,0,0,0,0,0,-1,0));
        return new Ability(caster,generator);
    }
    public Ability vendRadialAttack(Character caster) {
        RadialHitBoxGenerator generator = new RadialHitBoxGenerator(caster, new StatsAddable(0,0,0,0,0,0,0,-1,0));
        return new Ability(caster,generator);
    }
    public Ability vendDomeAttack(Character caster) {
        DomeHitBoxGenerator generator = new DomeHitBoxGenerator(caster, new StatsAddable(0,0,0,0,0,0,0,-1,0));
        return new Ability(caster,generator);
    }
}