package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 4/16/2016.
 */
public class Filled3DHex {
    private ArrayList<Location> locations = new ArrayList<>();

    public Filled3DHex(Location center, int radius) {
        Location loc = new Location(center.getR(),center.getS(),center.getZ());
        int bottom = ((loc.getZ() - radius) >= 0)? loc.getZ()-radius : 0;
        for ( int j = bottom; j < center.getZ() + radius; ++j) {

            loc.setZ(j);

            for (int i = 0; i < radius; i++) {
                (new HexRing(i, loc)).iterator().forEachRemaining((location -> locations.add(location)));
            }
        }
    }
    public Filled3DHex(Location center, int radius, int height) {
        Location loc = new Location(center.getR(),center.getS(),center.getZ());
        int bottom = ((loc.getZ() - height) >= 0)? loc.getZ()-height : 0;
        int top = ((loc.getZ() + height) <= 20)? loc.getZ()+height : 20;
        for ( int j = bottom; j < top; ++j) {

            loc.setZ(j);

            for (int i = 0; i < radius; i++) {
                (new HexRing(i, loc)).iterator().forEachRemaining((location -> locations.add(location)));
            }
        }
    }

    public Iterator<Location> iterator() {
        return locations.iterator();
    }
}
