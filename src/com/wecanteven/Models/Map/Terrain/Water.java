package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Water extends Terrain {
    @Override
    public void accept(TerrainVisitor visitor) {
        visitor.visitWater(this);
    }

    public String getTerrain() {
        return "Water";
    }
    public void interact(Entity entity){}
}
