package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private int castTicks,cooldownTicks;
    private int baseFailChance;
    private int skill;
    private MeleeRangeHitBoxGenerator hitBoxGenerator;

    public Ability(Character caster){
        hitBoxGenerator = new MeleeRangeHitBoxGenerator(caster);
    }
    public void cast(){
        hitBoxGenerator.generate();
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
