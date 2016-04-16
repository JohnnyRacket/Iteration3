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
        for (int j = 0; j < 20; ++j) {

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
