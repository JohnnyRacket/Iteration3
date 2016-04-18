package com.wecanteven.Models.Map;

import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Visitors.MapVisitor;
import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by John on 4/17/2016.
 */
public class FallDetector implements TerrainVisitor, MapVisitor {
    private boolean takeDamage = true;

    @Override
    public void visitWater(Water water) {
        takeDamage = false;
    }

    @Override
    public void visitGround(Ground ground) {
        takeDamage = true;
    }

    @Override
    public void visitAir(Air air) {
        takeDamage = true;
    }

    @Override
    public void visitCurrent(Current current) {
        takeDamage = false;
    }

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitTile(Tile tile) {
        takeDamage = true;
        tile.getTerrain().accept(this);
    }

    public boolean takeDamage() {
        return takeDamage;
    }

    public void setTakeDamage(boolean takeDamage) {
        this.takeDamage = takeDamage;
    }
}
