package com.wecanteven.Visitors.CanMoveVisitors;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;

/**
 * Created by John on 4/1/2016.
 */
public class TerranianCanMoveVisitor extends CanMoveVisitor {
    //no difference from default


    @Override
    public void visitWater(Water water) {
        setCanMove(true);
        setCanMoveBelow(true);
    }

    @Override
    public void visitGround(Ground ground) {
        setCanMove(false);
        setCanMoveBelow(true);
    }

    @Override
    public void visitAir(Air air) {
        setCanMove(true);
        setCanMoveBelow(true);
    }

    @Override
    public void visitCurrent(Current current) {
        setCanMove(true);
        setCanMoveBelow(true);
    }
}
