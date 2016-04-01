package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.TakeableItem;
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
    public boolean add(TakeableItem takeableItem, int z){
        return tiles.get(z).add(takeableItem);
    }
    public boolean add(OneShot oneShot, int z){
        return tiles.get(z).add(oneShot);
    }
    public boolean add(Obstacle obstacle, int z){
        return tiles.get(z).add(obstacle);
    }
    public boolean add(InteractiveItem interactiveItem, int z){
        return tiles.get(z).add(interactiveItem);
    }
}
