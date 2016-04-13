package com.wecanteven.Models.Decals;

import com.wecanteven.Visitors.DecalVisitor;

/**
 * Created by alexs on 4/13/2016.
 */
public class GroundDecal extends Decal {
    public GroundDecal(double r, double s) {
        super(r, s);
    }

    @Override
    public void accept(DecalVisitor v) {
        v.visitGroundDecal(this);
    }
}
