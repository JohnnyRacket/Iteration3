package com.wecanteven.AreaView;

import com.wecanteven.Location;

/**
 * Created by alexs on 3/29/2016.
 */
public class Position {
    private double r;
    private double s;
    private double z;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Position(double r, double s, double z) {

        this.r = r;
        this.s = s;
        this.z = z;
    }

    public Location getLocation() {
        return new Location((int)r,(int)s,(int)z);
    }
}
