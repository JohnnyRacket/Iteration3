package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
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
    private StatsAddable effect;

    public RadialHitBoxGenerator(Character caster,StatsAddable effect){
        distance = 1;
        this.effect = effect;
        this.caster = caster;

    }
    public void generate(){
        size = 1;
        generateRing(distance);
    }

    private void generateRing(int ringSize){
        HexRing hexring = new HexRing(ringSize,caster.getLocation());
        Iterator<Location> iterator = hexring.iterator();
        while(iterator.hasNext()){
            Location location = iterator.next();
            ViewTime.getInstance().register(()->{
                hitbox = new HitBox("Punch", caster.getLocation().adjacent(caster.getDirection()), effect, caster.getActionHandler(), 1200);
                hitbox.addToMap(1, location);
                ++distance;
                if (distance <= size) {
                    generate();
                }
            },200);
        }
    }
}
