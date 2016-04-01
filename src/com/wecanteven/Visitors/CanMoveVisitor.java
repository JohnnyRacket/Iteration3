package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Map.TileSlot;

/**
 * Created by John on 4/1/2016.
 */
public abstract class CanMoveVisitor implements MapVisitor, TerrainVisitor{

    private boolean canMove = true;

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitColumn(Column column) {

    }

    @Override
    public void visitTile(Tile tile) {
        setCanMove(true);//set true at beginning, aka can move until proven it cannot

        tile.getEntity().accept(this);
        tile.getObstacle().accept(this);
        tile.getTerrain().accept(this);
    }

    @Override
    public void visitTileSlot(TileSlot tileSlot) {
        if(!tileSlot.isEmpty()){setCanMove(false);}
    }

    @Override
    public void visitWater(Water water) {
        setCanMove(false);
    }

    @Override
    public void visitGround(Ground ground) {

    }

    @Override
    public void visitAir(Air air) {
        setCanMove(false);
    }

    @Override
    public void visitCurrent(Current current) {
        setCanMove(false);
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
