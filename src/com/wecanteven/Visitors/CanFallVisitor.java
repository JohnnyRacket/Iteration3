package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;

/**
 * Created by John on 4/4/2016.
 */
public abstract class CanFallVisitor implements MapVisitor, TerrainVisitor {

    private boolean canMove = true;

    @Override
    public void visitMap(Map map) {

    }


    @Override
    public void visitTile(Tile tile) {
        setCanMove(true);
        if (tile.hasEntity() || tile.hasObstacle() ) setCanMove(false);
        else {
            tile.getTerrain().accept(this);
        }
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
