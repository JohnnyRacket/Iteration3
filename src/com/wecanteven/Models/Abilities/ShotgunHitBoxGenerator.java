package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.FilledHex;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by Brandon on 4/16/2016.
 */
public class ShotgunHitBoxGenerator implements HitBoxGenerator {
    private com.wecanteven.Models.Entities.Character caster;
    private HitBox hitbox;
    private int distance,size;
    private Effects effect;

    public ShotgunHitBoxGenerator(Character caster,Effects effect){
        this.effect = effect;
        this.caster = caster;

    }
    public void generate(){
        for(int a=0;a<3;a++){

        }
    }

    private void generateDome(Location location,int ringSize){
        FilledHex filledHex = new FilledHex(location,ringSize);
        Iterator<Location> iterator = filledHex.iterator();
        while(iterator.hasNext()){
            Location tileLocation = iterator.next();
            if(!tileLocation.equals(caster.getLocation())){
                hitbox = new HitBox("Punch",tileLocation, effect, caster.getActionHandler(), 1200);
                hitbox.addToMap(1, tileLocation);
            }
        }
        --size;
        location = location.add(Direction.UP.getCoords);
        if (size > 0) {
            generateDome(location,size);
        }

    }
}