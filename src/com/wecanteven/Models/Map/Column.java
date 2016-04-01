package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Column implements MapVisitable {
    private ArrayList<Tile> tiles;

    public Column(){
        tiles = new ArrayList<Tile>();
    }

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitColumn(this);
    }

    public boolean add(Entity entity, int z){
        return tiles.get(z).add(entity);
    }
}
