package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.FilledHex;
import com.wecanteven.UtilityClasses.HexRing;
import com.wecanteven.UtilityClasses.Location;

import java.util.Iterator;

/**
 * Created by Brandon on 4/15/2016.
 */
public class RadialHitBoxGenerator implements HitBoxGenerator {
    private Character caster;
    private HitBox hitbox;
    private int distance,size;
    private Effects effect;

    public RadialHitBoxGenerator(Character caster,Effects effect){
        distance = 1;
        this.effect = effect;
        this.caster = caster;

    }
    public void generate(){
        size = 3;
        //generateRing(size);
        generateRing(distance);
    }

    private void generateRing(int ringSize){
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
        HexRing hexRing = new HexRing(ringSize,caster.getLocation());
        Iterator<Location> iterator = hexRing.orderedIterator();
        ViewTime.getInstance().register(()->{
        while(iterator.hasNext()){
            Location location = iterator.next();

                //if(!location.equals(caster.getLocation())) {
                    hitbox = new HitBox("Punch", location, effect, caster.getActionHandler(), 1200);
                    hitbox.addToMap(1, location);
                //}
        }
        },30*distance);
        ++distance;
        if(distance < size){
            generateRing(distance);
        }
    }
}
