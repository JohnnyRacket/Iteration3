package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Observers.Moveable;

/**
 * Created by John on 3/31/2016.
 */
public abstract class Terrain implements TerrainVisitable {
    public abstract String getTerrain();
    public abstract void interact(Moveable moveable);
}
