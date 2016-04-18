package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.FilledHex;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by Brandon on 4/16/2016.
 */
public class DomeHitBoxGenerator extends HitBoxGenerator {
    private int distance,size;

    private String name;
    private Location location;
    private Effects effect;

    public DomeHitBoxGenerator(String name, Character caster,Effects effect,int duration){
        setDuration(duration);
        setCaster(caster);
        setDistance(1);
        setHitBox(new HitBox(name,caster.getLocation(),effect,caster.getActionHandler(),1200));

        this.name = name;
        this.location = caster.getLocation();
        this.effect = effect;

    }
    public void generate(){
        generateDome(getCaster().getLocation(),size);
    }

    private void generateDome(Location location,int ringSize){
        FilledHex filledHex = new FilledHex(location,ringSize);
        Iterator<Location> iterator = filledHex.iterator();
        while(iterator.hasNext()){
            Location tileLocation = iterator.next();
            if(!tileLocation.equals(getCaster().getLocation())){
                HitBox temp = new HitBox(name,location,effect,getCaster().getActionHandler(),1200);
                temp.addToMap(getDuration(), tileLocation);
            }
        }
        --size;
        location = location.add(Direction.UP.getCoords);
        if (size > 0) {
            generateDome(location,size);
        }
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public void setSize(int size){
        this.size = size;
    }
}
