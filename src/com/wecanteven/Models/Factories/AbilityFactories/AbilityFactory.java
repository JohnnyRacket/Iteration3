package com.wecanteven.Models.Factories.AbilityFactories;

/**
 * Created by Brandon on 4/11/2016.
 */

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Abilities.Effects.BuffEffect;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Abilities.Effects.StatsEffect;
import com.wecanteven.Models.Abilities.HitBoxGenerator;
import com.wecanteven.Models.Abilities.MeleeRangeHitBoxGenerator;
import com.wecanteven.Models.Abilities.SelfHitBoxGenerator;
import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Models.Stats.StatsAddable;
/**
 * Created by Brandon on 4/11/2016.
 */
public class AbilityFactory {
    private String abilityName, abilityImage;
    private int duration;
    private Effects effect;
    private HitBoxGenerator generator;
    private int statLevel,skillLevel;
    private Skill skill;
    private Ability ability;


    //bind wounds ability
    public Ability vendBindWounds(Character caster){
        duration = 1;
        abilityName = "BindWounds";
        abilityImage = "Punch";
        skill = Skill.BIND_WOUNDS;
        statLevel = caster.getStats().getIntellect();
        skillLevel = caster.getSkillPoints(skill);
        int multiplier = statLevel+skillLevel;
        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,1*(multiplier),0));
        generator = new SelfHitBoxGenerator(abilityImage,caster,effect,duration);
        ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(30);
        ability.setWindUpTicks(30);
        return ability;
    }

    //One Handed Weapon ability
    public Ability vendOneHandedWeapon(Character caster) {
        skill = Skill.ONE_HANDED_WEAPON;
        statLevel = caster.getStats().getStrength();
        skillLevel = caster.getSkillPoints(skill);
        int multiplier = statLevel+skillLevel;
        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1*(multiplier),0));
        duration = 1;
        abilityName = "OneHandedWeapon";
        abilityImage = "Punch";
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
        Ability ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(30);
        ability.setWindUpTicks(30);
        ability.setCast(true);
        return ability;
    }

    //Two Handed Weapon ability
    public Ability vendTwoHandedWeapon(Character caster) {
        skill = Skill.TWO_HANDED_WEAPON;
        statLevel = caster.getStats().getStrength();
        skillLevel = caster.getSkillPoints(skill);
        int multiplier = statLevel+skillLevel;
        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1*(multiplier),0));
        duration = 1;
        abilityName = "TwoHandedWeapon";
        abilityImage = "Punch";
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
        Ability ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(30);
        ability.setWindUpTicks(30);
        ability.setCast(true);
        return ability;
    }

    //brawling ability
    public Ability vendBrawling(Character caster) {
        skill = Skill.BRAWLING;
        statLevel = caster.getStats().getStrength();
        skillLevel = caster.getSkillPoints(skill);
        int multiplier = statLevel+skillLevel;
        effect = new StatsEffect(new StatsAddable(0,0,0,0,0,0,0,-1*(multiplier),0));
        duration = 1;
        abilityName = "Brawling";
        abilityImage = "Punch";
        MeleeRangeHitBoxGenerator generator = new MeleeRangeHitBoxGenerator(abilityImage,caster,effect,duration);
        Ability ability = new Ability(abilityName,caster,generator,skill);
        ability.setCooldownTicks(5);
        ability.setWindUpTicks(5);
        ability.setCast(true);
        return ability;
    }

    //extra abilities

    public Ability vendSpeedUp(Character caster){
        duration = 500;
        abilityName = "SpeedUp";
        abilityImage = "Punch";
        skill = Skill.BOON;
        statLevel = caster.getStats().getIntellect();
        skillLevel = caster.getSkillPoints(skill);
        int multiplier = statLevel+skillLevel;
        effect = new BuffEffect(new Buff("Speed",
                "Orange",
                duration,
                (target)-> target.modifyStatsAdditive((new StatsAddable(0,0,0,0,0,0,5*(multiplier),0,0))),
                (target)-> target.modifyStatsSubtractive((new StatsAddable(0,0,0,0,0,0,5*(multiplier),0,0)))));
        generator = new SelfHitBoxGenerator(abilityImage,caster,effect,duration);
        ability = new Ability(abilityName,caster,generator,skill);
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