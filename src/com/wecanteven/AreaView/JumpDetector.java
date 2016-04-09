package com.wecanteven.AreaView;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.*;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by alexs on 4/9/2016.
 */
public class JumpDetector implements TerrainVisitor {
    private boolean isJumping;
    private Map gameMap;
    public JumpDetector(Map gameMap) {
        this.gameMap = gameMap;
    }
    public boolean isJumping(Location start, Location end) {
        getBelowTerrain(end).accept(this);
        return isJumping || end.getZ() > start.getZ();
    }
    public boolean isJumping(Position start, Position end) {
        return isJumping(start.getLocation(), end.getLocation());
    }

    @Override
    public void visitWater(Water water) {
        isJumping = true;
    }

    @Override
    public void visitGround(Ground ground) {
        isJumping = false;
    }

    @Override
    public void visitAir(Air air) {
        isJumping = true;
    }

    @Override
    public void visitCurrent(Current current) {
        isJumping = false;
    }

    private Terrain getBelowTerrain(Location location) {
        return gameMap.getTile(location.add(new Location(0,0,-1))).getTerrain();
    }
}