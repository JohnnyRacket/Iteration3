package com.wecanteven.Visitors.CanFallVisitors;

import com.wecanteven.Models.Map.Terrain.*;

/**
 * Created by John on 4/4/2016.
 */
public class TerranianCanFallVisitor extends CanFallVisitor {
    private boolean visitedCurrent;
    @Override
    public void visitWater(Water water) {
        this.setCanMove(true);
        visitedCurrent = false;
    }

    @Override
    public void visitGround(Ground ground) {
        this.setCanMove(false);
        visitedCurrent = false;
    }

    @Override
    public void visitAir(Air air) {
        this.setCanMove(true);
        visitedCurrent = false;
    }

    @Override
    public void visitCurrent(Current current) {

        if(visitedCurrent){
            this.setCanMove(false);
        }
        else{
            this.setCanMove(true);
            visitedCurrent = true;
        }
    }
}
