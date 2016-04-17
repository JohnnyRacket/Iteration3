package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.ModelTime;


/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private String name;
    private int windUpTicks,cooldownTicks;
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
        caster.updateWindUpTicks(windUpTicks);
        System.out.println("Activating the spell");
        activateAbility();

    }
    private void activateAbility(){
        ModelTime.getInstance().registerAlertable(() -> {
            System.out.println("The spell was activated");
            hitBoxGenerator.generate();
            caster.updateCoolDownTicks(cooldownTicks);
        },caster.getWindUpTicks());
    }

    public String getName() {
        return name;
    }
    public int getCooldownTicks(){
        return cooldownTicks;
    }
    public int getWindUpTicks(){
        return windUpTicks;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCooldownTicks(int ticks){
        cooldownTicks = ticks;
    }
    public  void setWindUpTicks(int ticks){
        windUpTicks = ticks;
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
