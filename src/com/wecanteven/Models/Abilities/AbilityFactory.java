package com.wecanteven.Models.Abilities;

/**
 * Created by Brandon on 4/11/2016.
 */

import com.wecanteven.Models.Abilities.Effects.StatsEffect;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;

/**
 * Created by Brandon on 4/11/2016.
 */
public class AbilityFactory {
    public Ability vendMeleeAttack(Character caster) {
        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator("Punch",caster,effect);
        return new Ability("Punch",caster,generator);
    }
//    public Ability vendRangedAttack(Character caster) {
//        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        ProjectileHitBoxGenerator generator = new ProjectileHitBoxGenerator(caster,effect);
//        return new Ability(caster,generator);
//    }
//    public Ability vendRadialAttack(Character caster) {
//        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        RadialHitBoxGenerator generator = new RadialHitBoxGenerator(caster,effect);
//        return new Ability(caster,generator);
//    }
//    public Ability vendDomeAttack(Character caster) {
//        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        DomeHitBoxGenerator generator = new DomeHitBoxGenerator(caster,effect);
//        return new Ability(caster,generator);
//    }
}