package com.wecanteven.Models.Map;

import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Interactions.InteractionVisitor;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.TakeableMoveable;
import com.wecanteven.Models.Map.Aoe.AreaOfEffect;
import com.wecanteven.Models.Map.Terrain.Terrain;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Observers.ModelObservable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 3/31/2016.
 */
public class Tile implements MapVisitable {

    private TileSlot<Obstacle> obstacle = new TileSlot<>();
    private TileSlot<InteractiveItem> interactiveItem = new TileSlot<>();
    private TileSlot<OneShot> oneShot = new TileSlot<>();
    private ArrayList<TakeableMoveable> takeableItems = new ArrayList<>();
    private TileSlot<Entity> entity = new TileSlot<>();
    private ArrayList<HitBox> hitBoxes = new ArrayList<>();
    private ArrayList<AreaOfEffect> areasOfEffect = new ArrayList<>();
    private ArrayList<Decal> decals = new ArrayList<>();

    private Terrain terrain;

    public Tile(Terrain terrain){
        this.terrain = terrain;
    }

    // TODO make sure that entities on the tile before the aoe are registered

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTile(this);
    }

    public boolean add(AreaOfEffect aoe) {
        if (!areasOfEffect.contains(aoe)) {
            aoe.setObserved(entity);
            areasOfEffect.add(aoe);
            entity.modelAttach(aoe);
            return true;
        }

        return false;
    }

    public boolean add(Entity entity){

        if(this.entity.add(entity)){
            entity.lock();
            ModelTime.getInstance().registerAlertable(
                    () -> interactWithTile(entity)
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
    public boolean add(TakeableMoveable takeableItem) {
        if (this.takeableItems.add(takeableItem)) {
            terrain.interact(takeableItem);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(AreaOfEffect aoe) {
        if (areasOfEffect.contains(aoe)) {
            areasOfEffect.remove(aoe);
            entity.modelDettach(aoe);
        }

        return false;
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
        ArrayList<TakeableItem> list = new ArrayList<>();

        for (TakeableMoveable item : takeableItems) {
            list.add(item.extractItem());
        }

        return list;
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

    private void interactWithTile(Entity entity){
        //This interacts with tile you're on
        entity.unlock();
        hitBoxes.forEach(effect -> effect.interact(entity));
        terrain.interact(entity);
        if (!oneShot.isEmpty()) {
            oneShot.getToken().interact(entity);
            remove(oneShot.getToken());
        }
        if (!interactiveItem.isEmpty()){
            interactiveItem.getToken().trigger();
        }

        for (AreaOfEffect aoe : areasOfEffect) {
            aoe.apply(entity);
        }

        interactWithCharacter((Character) entity);
    }


    private void interactWithCharacter(Character character) {
        ArrayList<TakeableMoveable> leftover = new ArrayList<>();
        for (TakeableMoveable i : takeableItems) {
            if (!character.getItemStorage().inventoryIsFull()) {
                i.extractItem().interact(character);
            } else {
                leftover.add(i);
            }
        }

        takeableItems = leftover;
    }

    public void interact(Character character) {
        if(hasEntity()) {
            InteractionVisitor visitor = new InteractionVisitor(character);
            getEntity().accept(visitor);
        }else {

        }
    }
    public void update(){
        if(hasEntity())
        interactWithTile(getEntity());
    }

    public boolean add(HitBox hitBox){

        if(hitBoxes.add(hitBox) && hasEntity()){
           hitBox.interact(getEntity());
            return true;
        }
        return false;
    }
    public boolean add(Decal decal) {
        decals.add(decal);
        return true;
    }
    public boolean remove(HitBox hitBox){
        return hitBoxes.remove(hitBox);
    }
    public ArrayList<HitBox> getEffects(){
        return getEffects();
    }
    public ArrayList<Decal> getDecals() { return decals; }
    public boolean hasEffect(){
        return !hitBoxes.isEmpty();
    }
    public boolean hasAoe() { return areasOfEffect.size() > 0;}

    public boolean isEmpty() {

        if(obstacle.isEmpty() && interactiveItem.isEmpty() &&
                oneShot.isEmpty() && takeableItems.isEmpty() &&
                entity.isEmpty() && areasOfEffect.isEmpty() && decals.isEmpty()) {
            return true;
        }
        return false;
    }

    public Iterator<AreaOfEffect> getAreasOfEffect() {
        return areasOfEffect.iterator();
    }

    /**
     * Created by John on 3/31/2016.
     */
    public class TileSlot<T> implements ModelObservable {
        private T t;

        private ArrayList<Observer> observers = new ArrayList<>();

        public T getToken() {
            return t;
        }

        public boolean add(T t){
            if(this.isEmpty()){
                this.t = t;
                modelNotifyObservers();
                return true;
            }else{
                return false;
            }
        }

        public boolean remove(T t){
            if(this.t == t){
                this.t = null;
                modelNotifyObservers();
                return true;
            }else{
                return false;
            }
        }

        public boolean isEmpty(){
            return (this.t == null);
        }

        @Override
        public ArrayList<Observer> getModelObservers() {
            return observers;
        }

        @Override
        public void modelAttach(Observer o) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }

        @Override
        public void modelDettach(Observer o) {
            if (observers.contains(o)) {
                observers.remove(o);
            }
        }

        @Override
        public void modelNotifyObservers() {
            for (Observer o : observers) {
                o.update();
            }
        }
    }
}
