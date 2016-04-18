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

    public Iterator<Location> orderedIterator() {
        ArrayList<Location> locations = new ArrayList<>();

        if (radius == 0) {
            locations.add(center);
        } else {
            int r = center.getR();
            int s = center.getS();
            int z = center.getZ();


            //ORDERED HEXRING
            //NORTH
            Location north = new Location(r,s-radius,z);
            locations.add(north);
            for (int n = 1; n <= radius; n++) {
                locations.add(new Location(north.getR()+n,north.getS(),z));
            }
            //NORTHEAST
            Location northEast = new Location(r+radius,s-radius,z);
            for(int n = 1; n <= radius; n++){
                locations.add(new Location(northEast.getR(),northEast.getS()+n,z));
            }
            //SOUTHEAST
            Location southEast = new Location(r+radius,s,z);
            for(int n = 1; n <= radius; n++){
                locations.add(new Location(southEast.getR()-n,southEast.getS()+n,z));
            }
            //SOUTH
            Location south = new Location(r,s+radius,z);
            for(int n = 1; n <= radius; n++){
                locations.add(new Location(south.getR()-n,south.getS(),z));
            }
            //SOUTHWEST
            Location southWest = new Location(r-radius,s+radius,z);
            for(int n = 1; n <= radius; n++){
                locations.add(new Location(southWest.getR(),southWest.getS()-n,z));
            }
            //NORTHEWEST
            Location northWest = new Location(r-radius,s,z);
            for(int n = 1; n < radius; n++){
                locations.add(new Location(northWest.getR()+n,northWest.getS()-n,z));
            }
        }
        saftey(locations);
        return locations.iterator();
    }

    public Iterator<Location> iteratorWithCenter() {
        ArrayList<Location> locations = new ArrayList<>();


            locations.add(center);

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

        saftey(locations);
        return locations.iterator();
    }

    private void saftey(ArrayList<Location> locations) {

    }
}
