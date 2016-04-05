package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

/**
 * Created by John on 4/4/2016.
 */
public class TerranianCanFallVisitor extends CanFallVisitor {
    @Override
    public void visitWater(Water water) {
        this.setCanMove(true);
    }

    @Override
    public void visitGround(Ground ground) {
        this.setCanMove(false);
    }

    @Override
    public void visitAir(Air air) {
        this.setCanMove(true);
    }

    @Override
    public void visitCurrent(Current current) {
        this.setCanMove(true);
    }
}
