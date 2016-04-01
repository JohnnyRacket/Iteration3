package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by John on 3/31/2016.
 */
public interface TerrainVisitable {
    void accept(TerrainVisitor visitor);
}
