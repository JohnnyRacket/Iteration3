package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.UtilityClasses.HexRing;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by Brandon on 4/15/2016.
 */
public class RadialHitBoxGenerator extends HitBoxGenerator {
    private int distance, size;

    private String name;
    private Location location;
    private Effects effect;

    public RadialHitBoxGenerator(String name, Character caster, Effects effect, int duration) {
        setCaster(caster);
        setDuration(duration);
        setDistance(1);
        setHitBox(new HitBox(name, caster.getLocation(), effect, caster.getActionHandler(), 1200));

        this.name = name;
        this.location = caster.getLocation();
        this.effect = effect;
    }

    public void generate() {
        generateRing(getDistance());
        //generateRing(size);
    }

    private void generateRing(int ringSize) {
        HexRing hexRing = new HexRing(ringSize, getCaster().getLocation());
        Iterator<Location> iterator = hexRing.orderedIterator();
        ViewTime.getInstance().register(() -> {
            while (iterator.hasNext()) {
                Location location = iterator.next();
                HitBox temp = new HitBox(name, getCaster().getLocation(), effect, getCaster().getActionHandler(), 1200);
                temp.addToMap(1, location);
            }
        }, 30 * getDistance());
        ++distance;
        if (getDistance() < getSize()) {
            generateRing(getDistance());
        }
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public void setSize(int size){
        this.size = size;
    }

    public int getDistance(){
        return distance;
    }
    public int getSize(){
        return size;
    }
}
//    private void generateRing(int ringSize){
//        HexRing hexring = new HexRing(ringSize,caster.getLocation());
//        Iterator<Location> iterator = hexring.iterator();
//        while(iterator.hasNext()){
//            Location location = iterator.next();
//            ViewTime.getInstance().register(()->{
//                hitbox = new HitBox("Punch", location, effect, caster.getActionHandler(), 1200);
//                hitbox.addToMap(1, location);
//            },30*distance);
//            ++distance;
//            --size;
//            if (size > 0) {
//                generateRing(size);
//            }
//        }
//    }
