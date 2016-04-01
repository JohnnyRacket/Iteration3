package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;

/**
 * Created by John on 3/31/2016.
 */
public interface MapVisitor {
    void visitMap(Map map);
    void visitTile(Tile tile);
}
