package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/7/2016.
 */
public class MeleeRangeHitBoxGenerator extends HitBoxGenerator {


    public MeleeRangeHitBoxGenerator(String name, Character caster,Effects effect,int duration){
        setDuration(duration);
        setCaster(caster);
        setHitBox(new HitBox(name,caster.getLocation(),effect,caster.getActionHandler(),300));
    }
    public void generate(){
        Direction casterDirection = getCaster().getDirection();
        Location startLocation = getCaster().getLocation();
        Location destination = startLocation.add(casterDirection.getCoords);
        getHitBox().addToMap(getDuration(), destination);
    }
}
