package com.wecanteven.UtilityClasses;

import com.wecanteven.AreaView.Position;

/**
 * Created by alexs on 3/29/2016.
 */
public class Location {
    private int r;
    private int s;
    private int z;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Location(int r, int s, int z) {

        this.r = r;
        this.s = s;
        this.z = z;
    }

    public Position toPosition() {
        return new Position(r,s,z);
    }

    public Location add(Location loc){
        return new Location(this.r + loc.getR(), this.s + loc.getS(), this.z + loc.getZ());
    }
    public Location subtract(Location loc){
        return new Location(this.r - loc.getR(), this.s - loc.getS(), this.z - loc.getZ());
    }

    public Location adjacent(Direction d) {
        return add(d.getCoords);
    }


    @Override
    public boolean equals(Object obj) {
        Location location = (Location) obj;
        return location.getR() == r &&
                location.getS() == s &&
                location.getZ() == z;
    }

    public String toString(){
        return ""+getR()+","+getS()+","+getZ();
    }
}
