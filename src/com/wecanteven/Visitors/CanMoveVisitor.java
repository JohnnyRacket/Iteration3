package com.wecanteven.Visitors;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Map.Tile;

/**
 * Created by John on 4/1/2016.
 */
public abstract class CanMoveVisitor implements MapVisitor, TerrainVisitor {

    private boolean canMove = true;
    private boolean canMoveBelow = true;
    private Entity entity;

    @Override
    public void visitMap(Map map) {

    }


    @Override
    public void visitTile(Tile tile) {
        setCanMove(true);
        setCanMoveBelow(true);
        if ((tile.hasEntity() && tile.getEntity()!= this.getEntity() )|| tile.hasObstacle() ) setCanMove(false);
        else {
            tile.getTerrain().accept(this);
        }
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean CanMoveBelow() {
        return canMoveBelow;
    }

    public void setCanMoveBelow(boolean canMoveBelow) {
        this.canMoveBelow = canMoveBelow;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
