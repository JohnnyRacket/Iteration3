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
    //melee ability example
    public Ability vendPunchAttack(Character caster) {
        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        int duration = 1;
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator("Punch",caster,effect,duration);
        return new Ability("Punch",caster,generator);
    }
    //projectile ability example
    public Ability vendWaterBolt(Character caster) {
        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        ProjectileHitBoxGenerator generator = new ProjectileHitBoxGenerator("WaterBolt",caster,effect);
        generator.setDistance(5);
        generator.setSpeed(30);
        return new Ability("WaterBolt",caster,generator);
    }
    //radial ability example
    public Ability vendRadialAttack(Character caster) {
        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        int duration = 1;
        RadialHitBoxGenerator generator = new RadialHitBoxGenerator("Punch",caster,effect,duration);
        generator.setSize(3);
        return new Ability("Punch",caster,generator);
    }
    //dome ability example
    public Ability vendDomeAttack(Character caster) {
        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        int duration = 1;
        DomeHitBoxGenerator generator = new DomeHitBoxGenerator("Punch",caster,effect,duration);
        generator.setDistance(1);
        generator.setSize(5);
        return new Ability("Punch",caster,generator);
    }
}