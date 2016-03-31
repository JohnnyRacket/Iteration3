package com.wecanteven.Models.Map;

import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Tile implements MapVisitable {

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTile(this);
    }
}
