package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Brandon on 4/7/2016.
 */
public class MeleeRangeHitBoxGenerator {
    private Character caster;
    private ArrayList<Location> locations;
    private StatsAddable effect;

    public MeleeRangeHitBoxGenerator(Character caster){
        this.caster = caster;
        locations = new ArrayList();
        effect = new StatsAddable(0,0,0,0,0,0,0,-5,0);
    }
    public void generate(){
        locations = new ArrayList<>();
        Location location = caster.getLocation();
        Direction direction = caster.getDirection();
        locations.add(0, location.add(direction.getCoords));
        caster.cast(locations, effect);
    }
}
