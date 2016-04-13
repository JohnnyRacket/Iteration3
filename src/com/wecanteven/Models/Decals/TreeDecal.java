package com.wecanteven.Models.Decals;

import com.wecanteven.Visitors.DecalVisitor;

/**
 * Created by alexs on 4/13/2016.
 */
public class TreeDecal extends Decal {
    public TreeDecal(double r, double s) {
        super(r, s);
    }

    @Override
    public void accept(DecalVisitor v) {
        v.visitTreeDecal(this);
    }
}
