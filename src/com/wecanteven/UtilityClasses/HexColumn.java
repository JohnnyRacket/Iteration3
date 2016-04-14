package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/14/2016.
 */
public class HexColumn {
    private Location startPoint;
    private int height;

    public HexColumn(Location startPoint, int height) {
        this.startPoint = startPoint;
        this.height = height;
    }

    public Iterator<Location> iterator() {
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(startPoint);
        Location lastPoint = startPoint;
        for (int i = 1; i<height; i++) {
            lastPoint = lastPoint.add(new Location(0,0,1));
            locations.add(lastPoint);
        }
        return locations.iterator();
    }
}
