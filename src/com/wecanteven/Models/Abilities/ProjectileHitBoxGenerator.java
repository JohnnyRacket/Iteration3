package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/7/2016.
 */
public class ProjectileHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    private ActionHandler actionHandler;
    HitBox hitbox;

    public ProjectileHitBoxGenerator(Character caster,StatsAddable effect){
        this.caster = caster;
        actionHandler = caster.getActionHandler();
        hitbox = new HitBox(effect);
    }
    public void generate(){

    }
}