package com.wecanteven.Models.Map;

import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Terrain.Terrain;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Tile implements MapVisitable {

    private TileSlot<Obstacle> obstacle = new TileSlot<>();
    private TileSlot<InteractiveItem> interactiveItem = new TileSlot<>();
    private TileSlot<OneShot> oneShot = new TileSlot<>();
    private ArrayList<TakeableItem> takeableItems = new ArrayList<>();
    private TileSlot<Entity> entity = new TileSlot<>();
    private ArrayList<HitBox> hitBoxes = new ArrayList<>();

    private Terrain terrain;

    public Tile(Terrain terrain){
        this.terrain = terrain;
    }


    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTile(this);
    }

    public boolean add(Entity entity){

        if(this.entity.add(entity)){
            entity.lock();
            ModelTime.getInstance().registerAlertable(
                    () -> interact(entity)
                    , entity.getMovingTicks() + 1);
            return true;
        }else{
            return false;
        }
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

    public boolean remove(Entity entity) {
        if (!interactiveItem.isEmpty())
            interactiveItem.getToken().release();
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

    public boolean hasEntity() {return !this.entity.isEmpty();}
    public boolean hasObstacle() { return !this.obstacle.isEmpty(); }
    public boolean hasInteractiveItem() {return !this.interactiveItem.isEmpty();}
    public boolean hasOneShot() { return !this.oneShot.isEmpty(); }

    public Obstacle getObstacle() {
        return obstacle.getToken();
    }

    public InteractiveItem getInteractiveItem() {
        return interactiveItem.getToken();
    }

    public OneShot getOneShot() {
        return oneShot.getToken();
    }

    public ArrayList<TakeableItem> getTakeableItems() {
        return takeableItems;
    }

    public Entity getEntity() {
        return entity.getToken();
    }


    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void interact(Entity entity){
        entity.unlock();
        hitBoxes.forEach( effect -> effect.interact(entity));
        terrain.interact(entity);
        if (!oneShot.isEmpty() )
            oneShot.getToken().interact(entity);
        if (!interactiveItem.isEmpty())
            interactiveItem.getToken().trigger();
    }



    public boolean add(HitBox hitBox){
        System.out.println("adding effects to the tile ");
        if(hitBoxes.add(hitBox) && hasEntity()){
           hitBox.interact(getEntity());
            return true;
        }
        return false;
    }
    public boolean remove(HitBox hitBox){
        return hitBoxes.remove(hitBox);
    }
    public ArrayList<HitBox> getEffects(){
        return getEffects();
    }
    public boolean hasEffect(){
        return !hitBoxes.isEmpty();
    }



    /**
     * Created by John on 3/31/2016.
     */
    public class TileSlot<T> {
        private T t;

        public T getToken() {
            return t;
        }

        public boolean add(T t){
            if(this.isEmpty()){
                this.t = t;
                return true;
            }else{
                return false;
            }
        }

        public boolean remove(T t){
            if(this.t == t){
                this.t = null;
                return true;
            }else{
                return false;
            }
        }

        public boolean isEmpty(){
            return (this.t == null);
        }
    }
}
