package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;

/**
 * Created by Brandon on 4/7/2016.
 */
public abstract class HitBoxGenerator {
    private Character caster;
    private HitBox hitBox;
    private int duration;

    abstract void generate();
    public void setCaster(Character caster){
        this.caster = caster;
    }
    public void setHitBox(HitBox hitBox){
        this.hitBox = hitBox;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Character getCaster(){
        return caster;
    }
    public HitBox getHitBox(){
        return hitBox;
    }
    public int getDuration(){
        return duration;
    }
}
