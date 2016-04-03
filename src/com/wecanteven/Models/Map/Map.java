package com.wecanteven.Models.Map;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable, ActionHandler {

    private Column[][] columns;
    private int rSize;
    private int sSize;
    private int zSize;

    public int getrSize() {
        return rSize;
    }

    public int getsSize() {
        return sSize;
    }

    public int getzSize() {
        return zSize;
    }

    public Map(int rSize, int sSize, int zSize){
        this.rSize = rSize;
        this.sSize = sSize;
        this.zSize = zSize;

        columns = new Column[rSize][sSize];
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < sSize; j++) {
                columns[i][j] = new Column();
            }
        }
    }

    private boolean isOutOfBounds(Location location){
        if( location.getR() < 0 ||
            location.getS() < 0 ||
            location.getZ() < 0 ||
            location.getR() > getrSize() ||
            location.getZ() > getzSize() ||
            location.getS() > getsSize()){
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Entity entity, Direction dir) {
        Location source = entity.getLocation();
        Location destination = source.add(dir.getCoords);
        if(isOutOfBounds(destination)){
            System.out.println("Out of Bounds");
            return false;
        }
        Tile tile = this.getTile(destination);
        tile.accept(entity.getCanMoveVisitor());

        if(entity.getCanMoveVisitor().canMove()) {
            System.out.println("Moving from "+ source + " to " + destination);
            remove(entity, source);
            add(entity,destination);
            return  true;
        }else{
            System.out.println("Couldn't Move");
            //can move visitor determined you cant move there
            return false;
        }
    }

    public Tile getTile(int r, int s, int z) {
        return columns[r][s].getTile(z);
    }

    @Override
    public boolean fall(Entity entity) {
        return false;
    }

    @Override
    public boolean move(TakeableItem item, Direction dir) {
        return false;
    }

    @Override
    public boolean fall(TakeableItem item) {
        return false;
    }

    @Override
    public boolean drop(TakeableItem item) {
        return false;
    }

    public Tile getTile(Location loc){
        return columns[loc.getR()][loc.getS()].getTile(loc.getZ());
    }

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitMap(this);
    }

    public boolean add(Entity entity, Location loc){
        entity.setLocation(loc);
        return columns[loc.getR()][loc.getS()].add(entity, loc.getZ());
    }
    public boolean add(OneShot oneShot, Location loc){
        return columns[loc.getR()][loc.getS()].add(oneShot, loc.getZ());
    }
    public boolean add(TakeableItem takeableItem, Location loc){
        return columns[loc.getR()][loc.getS()].add(takeableItem, loc.getZ());
    }
    public boolean add(Obstacle obstacle, Location loc){
        return columns[loc.getR()][loc.getS()].add(obstacle, loc.getZ());
    }
    public boolean add(InteractiveItem interactiveItem, Location loc){
        return columns[loc.getR()][loc.getS()].add(interactiveItem, loc.getZ());
    }

    public boolean remove(Entity entity, Location loc){
        return columns[loc.getR()][loc.getS()].remove(entity, loc.getZ());
    }
    public boolean remove(OneShot oneShot, Location loc){
        return columns[loc.getR()][loc.getS()].remove(oneShot, loc.getZ());
    }
    public boolean remove(TakeableItem takeableItem, Location loc){
        return columns[loc.getR()][loc.getS()].remove(takeableItem, loc.getZ());
    }
    public boolean remove(Obstacle obstacle, Location loc){
        return columns[loc.getR()][loc.getS()].remove(obstacle, loc.getZ());
    }
    public boolean remove(InteractiveItem interactiveItem, Location loc){
        return columns[loc.getR()][loc.getS()].remove(interactiveItem, loc.getZ());
    }

    /**
     * Created by John on 3/31/2016.
     */
    public static class Column  {
        private ArrayList<Tile> tiles;

        public Column(){
            tiles = new ArrayList<>();
            for (int i = 0; i <10; i++) {
                tiles.add(new Tile(new Air()));
            }
        }

        public Tile getTile(int zLevel) {
            return tiles.get(zLevel);
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
}
