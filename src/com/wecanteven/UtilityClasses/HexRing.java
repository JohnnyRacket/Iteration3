package com.wecanteven.UtilityClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/10/2016.
 */
public class HexRing {
    private int radius;
    private Location center;

    public HexRing(int radius, Location center) {
        this.radius = radius;
        this.center = center;
    }

    public Iterator<Location> iterator() {
        ArrayList<Location> locations = new ArrayList<>();

        if (radius == 0) {
            locations.add(center);
        } else {
            int r = center.getR();
            int s = center.getS();
            int z = center.getZ();

            for (int n = 0; n < radius; n++) {
                // Get top-right and bottom-left slabs
                locations.add(new Location(r + n, s - radius, z));
                locations.add(new Location(r - n, s + radius, z));

                //Get right and left slabs
                locations.add(new Location(r + radius, s - radius + n, z));
                locations.add(new Location(r - radius, s + radius - n, z));

                //Get bottom-right and top-left slabs
                locations.add(new Location(r + radius - n, s + n, z));
                locations.add(new Location(r - radius + n, s - n, z));
            }
        }
        saftey(locations);
        return locations.iterator();
    }

    private void saftey(ArrayList<Location> locations) {

    }
}
