package com.wecanteven.Models.Decals;

import com.wecanteven.Visitors.DecalVisitor;

/**
 * Created by alexs on 4/13/2016.
 */
public abstract class Decal {
    private double r;
    private double s;

    public Decal(double r, double s){
        this.r = r;
        this.s = s;

    }

    public abstract void accept(DecalVisitor v);

    public double getR() {
        return r;
    }

    public double getS() {
        return s;
    }

}
