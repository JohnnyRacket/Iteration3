package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/17/2016.
 */
public class SelfHitBoxGenerator extends HitBoxGenerator {
    public SelfHitBoxGenerator(String name, Character caster,Effects effect,int duration){
        setDuration(duration);
        setCaster(caster);
        setHitBox(new HitBox(name,caster.getLocation(),effect,caster.getActionHandler(),300));
    }
    public void generate(){
        Location location = getCaster().getLocation();
        getHitBox().addToMap(getDuration(), location);
    }
}