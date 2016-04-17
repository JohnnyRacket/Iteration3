package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.ModelTime;


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
        int windUpTime = 120;
        caster.updateWindUpTicks(windUpTime);
        System.out.println("Activating the spell");
        activateAbility();

    }
    private void activateAbility(){
        int coolDown = 120;
        ModelTime.getInstance().registerAlertable(() -> {
            System.out.println("The spell was activated");
            hitBoxGenerator.generate();
            caster.updateCoolDownTicks(coolDown);
        },caster.getWindUpTicks());
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
