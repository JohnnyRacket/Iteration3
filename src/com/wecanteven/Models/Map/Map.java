package com.wecanteven.Models.Map;

import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable {

    private ArrayList<Column> columns;

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitMap(this);
    }
}
