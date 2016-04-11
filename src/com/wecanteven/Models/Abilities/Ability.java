package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;


/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private int castTicks,cooldownTicks;
    private int baseFailChance;
    private int skill;
    private HitBoxGenerator hitBoxGenerator;
    private Character caster;

    public Ability(Character caster,HitBoxGenerator hitBoxGenerator){
        this.caster = caster;
        this.hitBoxGenerator = hitBoxGenerator;
    }
    public void cast(){
        hitBoxGenerator.generate();
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
