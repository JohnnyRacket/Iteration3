package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/7/2016.
 */
public class MeleeRangeHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    HitBox hitbox;

    public MeleeRangeHitBoxGenerator(Character caster,Effects effect){
        this.caster = caster;
        hitbox = new HitBox("Punch",caster.getLocation(),effect,caster.getActionHandler(),300);
    }
    public void generate(){
        int duration = 1;

        Direction casterDirection = caster.getDirection();
        Location startLocation = caster.getLocation();
        Location destination = startLocation.add(casterDirection.getCoords);
        hitbox.addToMap(duration,destination);
    }
}
