package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Water extends Terrain {
    @Override
    public void accept(TerrainVisitor visitor) {
        visitor.visitWater(this);
    }
}
