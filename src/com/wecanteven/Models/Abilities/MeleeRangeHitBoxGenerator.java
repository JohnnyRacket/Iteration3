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
public class MeleeRangeHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    private ActionHandler actionHandler;
    HitBox hitbox;

    public MeleeRangeHitBoxGenerator(Character caster,StatsAddable effect){
        this.caster = caster;
        actionHandler = caster.getActionHandler();
        hitbox = new HitBox("Melee",caster.getLocation(),effect);
    }
    public void generate(){
        System.out.println("The spell is occuring");
        Direction casterDirection = caster.getDirection();
        Location startLocation = caster.getLocation();
        Location destination = startLocation.add(casterDirection.getCoords);
        actionHandler.add(hitbox,destination);
        ModelTime modelTime = ModelTime.getInstance();
        modelTime.registerAlertable(new Alertable() {
            @Override
            public void alert() {
                actionHandler.remove(hitbox,destination);
            }
        }, 1);
    }
}
