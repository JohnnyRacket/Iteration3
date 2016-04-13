package com.wecanteven.Models.Decals;

import com.wecanteven.Visitors.DecalVisitor;

/**
 * Created by alexs on 4/13/2016.
 */
public class Decal {
    private double r;
    private double s;
    private String name;

    public Decal(String name, double r, double s){
        this.name = name;

        this.r = r;
        this.s = s;

    }

    public void accept(DecalVisitor v) {
        v.visitDecal(this);
    }

    public String getName() {
        return name;
    }

    public double getR() {
        return r;
    }

    public double getS() {
        return s;
    }

}
