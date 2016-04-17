package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.ModelTime;


/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private String name;
    private int castTicks,cooldownTicks;
    private int baseFailChance;
    private int skill;
    private HitBoxGenerator hitBoxGenerator;
    private Character caster;

    public Ability(String abilityName, Character caster,HitBoxGenerator hitBoxGenerator){
        this.name = abilityName;
        this.caster = caster;
        this.hitBoxGenerator = hitBoxGenerator;
    }
    public void cast(){
        int windUpTime = 30;
        caster.updateWindUpTicks(windUpTime);
        System.out.println("Activating the spell");
        activateAbility();

    }
    private void activateAbility(){
        int coolDown = 30;
        ModelTime.getInstance().registerAlertable(() -> {
            System.out.println("The spell was activated");
            hitBoxGenerator.generate();
            caster.updateCoolDownTicks(coolDown);
        },caster.getWindUpTicks());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
