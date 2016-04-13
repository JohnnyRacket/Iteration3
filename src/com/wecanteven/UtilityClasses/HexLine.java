package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/12/2016.
 */
public class HexLine {
    private Location startPoint;
    private Direction direction;
    private int length;

    public HexLine(Location startPoint, Direction direction, int length) {
        this.startPoint = startPoint;
        this.direction = direction;
        this.length = length;
    }

    public Iterator<Location> iterator() {
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(startPoint);
        Location lastPoint = startPoint;
        for (int i = 1; i<length; i++) {
            lastPoint = lastPoint.adjacent(direction);
            locations.add(lastPoint);
        }
        return locations.iterator();
    }
}
