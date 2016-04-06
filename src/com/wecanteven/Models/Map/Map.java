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
import com.wecanteven.Visitors.CanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.ColumnVisitor;
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
            location.getR() > getrSize()-1 ||
            location.getZ() > getzSize()-1 ||
            location.getS() > getsSize()-1){
            return true;
        }
        return false;
    }

    @Override
    public boolean fall(Entity entity, Location destination){
        CanFallVisitor visitor = entity.getCanFallVisitor();
        getTile(destination).accept(visitor);
        if(visitor.isCanMove()){
            return move(entity,destination);

        }else{
            return false;
        }
    }

    @Override
    public boolean move(Entity entity, Location destination) {
        Location source = entity.getLocation();
        CanMoveVisitor visitor = entity.getCanMoveVisitor();

        //checks if you are moving outside the bounds of the map
        if(isOutOfBounds(destination)){
            System.out.println("Out of Bounds");
            return false;
        }
        //System.out.println("destination is : " + destination);
        //checks to see if anything is blocking your height when moving
        boolean canMove = true;
        for(int i = 0; i < entity.getHeight() && canMove; ++i){
            Tile tile = this.getTile(destination.add(new Location(0,0,i)));
            tile.accept(visitor);
            canMove = canMove && visitor.canMove();
        }

        //checks the tile you will be standing on
        Tile tileBelow = this.getTile(destination.subtract(new Location(0,0,1)));
        tileBelow.accept(visitor);
        canMove = canMove && visitor.CanMoveBelow();


        if(canMove) {//move if you can
            remove(entity, source);
            add(entity, destination);
            return true;
        }else if(destination.getZ() < source.getZ()+entity.getJumpHeight()){
            //jump if you cant move
            return move(entity, destination.add(Direction.UP.getCoords));
      }else{
            //cant move or jump
            return false;
        }
    }

    public Tile getTile(int r, int s, int z) {
        return columns[r][s].getTile(z);
    }

    public Column getColumn(int r, int s) { return columns[r][s]; }

    public void setColumn(int r, int s, Column c) { columns[r][s] = c; }


    @Override
    public boolean move(TakeableItem item, Location location) {
        return false;
    }

    @Override
    public boolean fall(TakeableItem item, Location location) {
        return false;
    }

    @Override
    public boolean drop(TakeableItem item, Location location) {
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


}
