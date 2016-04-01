package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Terrain.Terrain;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Tile implements MapVisitable {

    private TileSlot<Obstacle> obstacle;
    private TileSlot<InteractiveItem> interactiveItem;
    private TileSlot<OneShot> oneShot;
    private ArrayList<TakeableItem> takeableItems = new ArrayList<>();
    private TileSlot<Entity> entity;

    private Terrain terrain;

    public Tile(Terrain terrain){
        this.terrain = terrain;
    }


    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTile(this);
    }

    public boolean add(Entity entity){
        return this.entity.add(entity);
    }
    public boolean add(OneShot oneShot){
        return this.oneShot.add(oneShot);
    }
    public boolean add(Obstacle obstacle) {
        return this.obstacle.add(obstacle);
    }
    public boolean add(InteractiveItem interactiveItem){
        return this.interactiveItem.add(interactiveItem);
    }
    public boolean add(TakeableItem takeableItem){
        this.takeableItems.add(takeableItem);
        return true;
    }

    public boolean remove(Entity entity){
        return this.entity.remove(entity);
    }
    public boolean remove(OneShot oneShot){
        return this.oneShot.remove(oneShot);
    }
    public boolean remove(Obstacle obstacle) {
        return this.obstacle.remove(obstacle);
    }
    public boolean remove(InteractiveItem interactiveItem){
        return this.interactiveItem.remove(interactiveItem);
    }
    public boolean remove(TakeableItem takeableItem){
        this.takeableItems.remove(takeableItem);
        return true;
    }

    public TileSlot<Obstacle> getObstacle() {
        return obstacle;
    }

    public TileSlot<InteractiveItem> getInteractiveItem() {
        return interactiveItem;
    }

    public TileSlot<OneShot> getOneShot() {
        return oneShot;
    }

    public ArrayList<TakeableItem> getTakeableItems() {
        return takeableItems;
    }

    public TileSlot<Entity> getEntity() {
        return entity;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
