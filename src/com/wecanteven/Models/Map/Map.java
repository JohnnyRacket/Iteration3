package com.wecanteven.Models.Map;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable, ActionHandler {

    private Column[][] columns;

    public Map(int rSize, int sSize){
        columns = new Column[rSize][sSize];
    }


    @Override
    public boolean move(Entity entity, Direction dir) {
        Tile tile = this.getTile(dir.getCoords);
        tile.accept(entity.getCanMoveVisitor());

        if(entity.getCanMoveVisitor().canMove()) {
            remove(entity, entity.getLocation());
            entity.setLocation(entity.getLocation().add(dir.getCoords));
            if (add(entity, entity.getLocation())) {
                return true;
            } else {
                entity.setLocation(entity.getLocation().subtract(dir.getCoords));
                add(entity, entity.getLocation());
                return false;
            }
        }else{
            //can move visitor determined you cant move there
            return false;
        }
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
