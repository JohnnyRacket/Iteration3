package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.FilledHex;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by Brandon on 4/16/2016.
 */
public class DomeHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    private HitBox hitbox;
    private int distance,size;
    private StatsAddable effect;

    public DomeHitBoxGenerator(Character caster,StatsAddable effect){
        distance = 1;
        this.effect = effect;
        this.caster = caster;

    }
    public void generate(){
        size = 5;
        generateDome(caster.getLocation(),size);
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
