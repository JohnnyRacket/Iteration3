package com.wecanteven.Models.Abilities;

/**
 * Created by Brandon on 4/11/2016.
 */

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Abilities.Effects.StatsEffect;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Models.Stats.Stat;
import com.wecanteven.Models.Stats.StatsAddable;
/**
 * Created by Brandon on 4/11/2016.
 */
public class AbilityFactory {
    private String abilityName, abilityImage;
    private int duration;
    private Effects baseEffect;
    private HitBoxGenerator generator;
    private Stat stat;
    private Skill skill;
    private Ability ability;


    //bind wounds skill
    public Ability vendBindWounds(Character caster){
        skill = Skill.BIND_WOUNDS;
        baseEffect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,1,0));
        Effects effect = baseEffect.update(caster.getSkillPoints(skill));
        duration = 1;
        abilityName = "Punch";
        //abilityImage = "BindWounds";
        abilityImage = "Punch";
        generator = new SelfHitBoxGenerator(abilityImage,caster,effect,duration);
        ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(30);
        ability.setWindUpTicks(30);
        return ability;
    }

    //brawling skill
    public Ability vendBrawling(Character caster) {
        skill = Skill.BRAWLING;
        baseEffect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
        Effects effect = baseEffect.update(caster.getSkillPoints(skill));
        System.out.println("skill level: "+caster.getSkillPoints(skill));
        duration = 1;
        abilityName = "Punch";
        abilityImage = "Punch";
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
        Ability ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(30);
        ability.setWindUpTicks(30);
        ability.setCast(true);
        return ability;
    }





//    //melee ability example
//    public Ability vendPunchAttack(Character caster) {
//        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        duration = 1;
//        abilityName = "Punch";
//        abilityImage = "Punch";
//        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
//        Ability ability = new Ability(abilityName,caster,generator);
//        ability.setCooldownTicks(5);
//        ability.setWindUpTicks(5);
//        return ability;
//    }
//    //healing touch ability example
//    public Ability vendHealingTouch(Character caster) {
//        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,10,0));
//        duration = 1;
//        abilityName = "HealingTouch";
//        abilityImage = "Punch";
//        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
//        Ability ability = new Ability(abilityName,caster,generator);
//        ability.setCooldownTicks(5);
//        ability.setWindUpTicks(5);
//        return ability;
//    }
//    //projectile ability example
//    public Ability vendWaterBolt(Character caster) {
//        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        ProjectileHitBoxGenerator generator = new ProjectileHitBoxGenerator(abilityImage,caster,effect);
//        generator.setDistance(5);
//        generator.setSpeed(30);
//        abilityName = "Punch";
//        abilityImage = "WaterBolt";
//        Ability ability = new Ability(abilityName,caster,generator);
//        ability.setCooldownTicks(15);
//        ability.setWindUpTicks(15);
//        return ability;
//    }
//    //radial ability example
//    public Ability vendRadialAttack(Character caster) {
//        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        int duration = 1;
//        abilityName = "Punch";
//        abilityImage = "Punch";
//        RadialHitBoxGenerator generator = new RadialHitBoxGenerator(abilityImage,caster,effect,duration);
//        generator.setSize(3);
//        Ability ability = new Ability(abilityName,caster,generator);
//        ability.setCooldownTicks(15);
//        ability.setWindUpTicks(15);
//        return ability;
//    }
//    //dome ability example
//    public Ability vendDomeAttack(Character caster) {
//        StatsEffect effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1,0));
//        int duration = 1;
//        abilityName = "Punch";
//        abilityImage = "Punch";
//        DomeHitBoxGenerator generator = new DomeHitBoxGenerator(abilityImage,caster,effect,duration);
//        generator.setDistance(1);
//        generator.setSize(5);
//        Ability ability = new Ability(abilityName,caster,generator);
//        ability.setCooldownTicks(15);
//        ability.setWindUpTicks(15);
//        return ability;
//    }
}