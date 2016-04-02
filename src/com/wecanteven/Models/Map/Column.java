package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
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

    public Tile getTile(int z){
        return tiles.get(z);
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

    public boolean remove(Entity entity, int z){
        return tiles.get(z).remove(entity);
    }
    public boolean remove(TakeableItem takeableItem, int z){
        return tiles.get(z).remove(takeableItem);
    }
    public boolean remove(OneShot oneShot, int z){
        return tiles.get(z).remove(oneShot);
    }
    public boolean remove(Obstacle obstacle, int z){
        return tiles.get(z).remove(obstacle);
    }
    public boolean remove(InteractiveItem interactiveItem, int z){
        return tiles.get(z).remove(interactiveItem);
    }

}
