package com.wecanteven.Visitors;

import com.wecanteven.Models.Decals.FlowerDecal;
import com.wecanteven.Models.Decals.GroundDecal;
import com.wecanteven.Models.Decals.TreeDecal;

/**
 * Created by alexs on 4/13/2016.
 */
public interface DecalVisitor {
    void visitFlowerDecal(FlowerDecal f);
    void visitGroundDecal(GroundDecal g);
    void visitTreeDecal(TreeDecal t);
}
