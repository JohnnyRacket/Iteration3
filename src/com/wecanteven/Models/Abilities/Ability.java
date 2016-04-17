package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Skill;

import java.util.Random;


/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private String name;
    private int windUpTicks,cooldownTicks;
    private int failChance;
    private boolean cast;
    private Skill skill;
    private HitBoxGenerator hitBoxGenerator;
    private Character caster;


    public Ability(String abilityName, Character caster,HitBoxGenerator hitBoxGenerator,Skill skill){
        this.name = abilityName;
        this.caster = caster;
        this.hitBoxGenerator = hitBoxGenerator;
        this.skill = skill;
        cast = false;
        failChance = 0;
    }
    public void cast(){
        if (caster.isActive()) {
            return;
        }
            calculateFailChance(getSkillLevel());
            Random randomGenerator = new Random();
            if (cast || getFailChance()<=randomGenerator.nextInt(100)) {
                System.out.println("windup"+getWindUpTicks());
                caster.updateWindUpTicks(windUpTicks);
                caster.updateCoolDownTicks(windUpTicks+cooldownTicks);
                System.out.println("Activating the spell");
                activateAbility();
                return;
            }
            caster.updateCoolDownTicks(cooldownTicks);
            System.out.println("The ability has failed");

    }
    private void activateAbility(){
        ModelTime.getInstance().registerAlertable(() -> {
            System.out.println("The spell was activated");
            hitBoxGenerator.generate();
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
    public int getFailChance(){
        return failChance;
    }
    public Skill getSkill(){
        return skill;
    }
    public int getSkillLevel(){
        return caster.getSkillPoints(getSkill());
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
    public void setCast(boolean success){
        cast = success;
    }


    private void calculateFailChance(int skillLevel){
        failChance = 90;                //default chance of 10%
        failChance -= 10*skillLevel;
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}
}
