package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Brandon on 4/16/2016.
 */
public class ShotgunHex {
    private int distance;
    private Location caster;
    private Direction direction;

    public ShotgunHex(int distance, Location caster, Direction direction) {
        this.distance = distance;
        this.caster = caster;
        this.direction = direction;
    }

    public Iterator<Location> shotgunBlastIterator() {
        ArrayList<Location> locations = new ArrayList<>();
        if (distance == 0) {
            locations.add(caster);
        } else {
            int r = caster.getR();
            int s = caster.getS();
            int z = caster.getZ();

            Location temp = caster;
            for(int i=1;i<=distance;i++){
                temp=temp.add(direction.getCoords);
                locations.add(temp);
                for(int j=1;j<i;j++){
                    locations.add(temp);
                }

            }

        }

        return locations.iterator();
    }
}
