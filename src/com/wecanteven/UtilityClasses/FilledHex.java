package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/14/2016.
 */
public class FilledHex {
    private ArrayList<Location> locations = new ArrayList<>();

    public FilledHex(Location center, int radius) {
        for (int i = 0; i<radius; i++) {
            (new HexRing(i, center)).iterator().forEachRemaining( (location -> locations.add(location)));
        }
    }

    public Iterator<Location> iterator() {
        return locations.iterator();
    }
}
