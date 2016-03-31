package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

/**
 * Created by John on 3/31/2016.
 */
public interface TerrainVisitor {

    void visitWater(Water water);
    void visitGround(Ground ground);
    void visitAir(Air air);
    void visitCurrent(Current current);
}
