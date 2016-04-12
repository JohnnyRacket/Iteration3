package com.wecanteven.Models.Map;

import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Aoe.AreaOfEffect;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Visitors.ColumnVisitor;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/5/2016.
 */
public class Column {

    private ArrayList<Tile> tiles;
    private int z = 10;
    public Column(){
        tiles = new ArrayList<>();
        for (int i = 0; i < z; i++) {
            tiles.add(new Tile(new Air()));
        }
    }
    public Column(int z, ArrayList<Tile> tiles) {
        this.z = z;
        this.tiles = tiles;

    }

    public Tile getTile(int zLevel) {
        return tiles.get(zLevel);
    }
    public int getZ() {
        return z;
    }

    public boolean add(AreaOfEffect aoe, int z) { return tiles.get(z).add(aoe); }
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
    public boolean add(HitBox hitBox, int z){
        return tiles.get(z).add(hitBox);
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
    public boolean remove(HitBox hitBox, int z){
        return tiles.get(z).remove(hitBox);
    }

    public void accept(ColumnVisitor visitor) {
        visitor.visitColumn(this);
    }

    public String toString() {
        String string = "";
        for (Tile t: tiles) {
            string += t.getTerrain().getTerrain() + " ";
        }
        return string;
    }
}
