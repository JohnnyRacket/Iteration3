package com.wecanteven.Visitors.CanFallVisitors;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

/**
 * Created by Cachorrita on 4/17/2016.
 */
public class FlyingCanFallVisitor extends CanFallVisitor {
    private boolean visitedCurrent;

    @Override
    public void visitWater(Water water) {
        this.setCanMove(false);
        visitedCurrent = false;
    }

    @Override
    public void visitGround(Ground ground) {
        this.setCanMove(false);
        visitedCurrent = false;
    }

    @Override
    public void visitAir(Air air) {
        this.setCanMove(false);
        visitedCurrent = false;
    }

    @Override
    public void visitCurrent(Current current) {
        this.setCanMove(false);
        visitedCurrent = true;
    }
}
