package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

/**
 * Created by John on 4/1/2016.
 */
public class TerranianCanMoveVisitor extends CanMoveVisitor {
    //no difference from default


    @Override
    public void visitWater(Water water) {
        setCanMove(false);
    }

    @Override
    public void visitGround(Ground ground) {
        setCanMove(false);
    }

    @Override
    public void visitAir(Air air) {
        setCanMove(true);
    }

    @Override
    public void visitCurrent(Current current) {
        setCanMove(true);
    }
}
