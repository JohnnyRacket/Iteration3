package com.wecanteven.Models.Map;

import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Column implements MapVisitable {

    private ArrayList<Tile> tiles;

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitColumn(this);
    }
}
