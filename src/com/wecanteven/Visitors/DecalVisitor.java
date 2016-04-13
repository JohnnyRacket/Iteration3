package com.wecanteven.Visitors;

import com.wecanteven.Models.Decals.Decal;


/**
 * Created by alexs on 4/13/2016.
 */
public interface DecalVisitor {
    void visitDecal(Decal d);
}
