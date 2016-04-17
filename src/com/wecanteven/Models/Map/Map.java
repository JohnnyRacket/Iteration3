package com.wecanteven.Models.Map;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Abilities.MovableHitBox;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.TakeableMoveable;
import com.wecanteven.Models.Map.Aoe.AreaOfEffect;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.*;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable, ActionHandler {
    private String name;
    private Column[][] columns;
    private int rSize;
    private int sSize;
    private int zSize;

    private VOCreationVisitor voCreationVisitor;
    public void setVOCreationVisitor(VOCreationVisitor visitor) {
        this.voCreationVisitor = visitor;
    }

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
                columns[i][j] = new Column(zSize);
            }
        }
    }

    private boolean isOutOfBounds(Location location){
        if( location.getR() < 0 ||
            location.getZ() < 0 ||
            location.getS() < 0 ||
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
        int tilesCount = 0;
        while(visitor.isCanMove()){
            destination.setZ(destination.getZ()-1);
            if(destination.getZ() <0){
                destination.setZ(destination.getZ()+2);
                move(entity,destination,2*tilesCount);
                entity.loseLife();
                return false;
            }
            getTile(destination).accept(visitor);
            tilesCount++;
        }
        if(tilesCount > 0) {
            destination.setZ(destination.getZ() + 1);
            return move(entity, destination, 2*tilesCount);
        }
        else{
            return false;
        }
    }

    @Override
    public boolean move(Entity entity, Location destination, int movespeed) {
        Location source = entity.getLocation();
        CanMoveVisitor visitor = entity.getCanMoveVisitor();
        System.out.println("############################## moving from " + source + " to " + destination);

        //checks if you are moving outside the bounds of the map
        if(isOutOfBounds(destination)){
            return false;
        }
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
            Column col = getColumn(entity.getLocation().getR(),entity.getLocation().getS());
            //when surrogates are added to the game.
            //for(int a=entity.getLocation().getZ()+entity.getHeight();a<col.getZ();a++){
            for(int a=entity.getLocation().getZ();a<col.getZ();a++){
                col.getTile(a).update();
            }
            entity.setLocation(destination);
            entity.updateMovingTicks(movespeed);
            add(entity, destination);
            return true;
        }else if(destination.getZ() < source.getZ()+entity.getJumpHeight()){
            //try to jump if you cant move
            //checks if you'll bump your head
            int count = 1;
            boolean bumpHead = true;
            for(int i = 0; i <entity.getHeight() && bumpHead;i++){
                Tile above = this.getTile(destination.subtract(entity.getDirection().getCoords).add(new Location(0,0,count)));
                above.accept(visitor);
                bumpHead = bumpHead & visitor.canMove();
                count++;
            }
            if(bumpHead){
                return move(entity, destination.add(Direction.UP.getCoords), movespeed);
            }
            else{
                //you bumped your head and could not jump
                return false;
            }
      }else{
            //cant move or jump
            return false;
        }
    }

    @Override
    public boolean move(MovableHitBox hitBox, Location destination, int moveSpeed) {
        Location source = hitBox.getLocation();
        CanMoveVisitor visitor = hitBox.getCanMoveVisitor();

        //checks if you are moving outside the bounds of the map
        if(isOutOfBounds(destination)){

            return false;
        }

        Tile tile = getTile(destination);
        tile.accept(visitor);
        if(visitor.canMove()) {//move if you can
            remove(hitBox,hitBox.getLocation());
            hitBox.setLocation(destination);
            hitBox.updateMovingTicks(moveSpeed);
            remove(hitBox, source);
            add(hitBox, destination);
            return true;
        }
        else{
            //reached an entity/obstacle/wall
            if(tile.hasEntity()){//hits the entity if there is one

                //Interact visitor to be called here!

              //  InteractionVisitor interactionVisitor = new InteractionVisitor(tile.getEntity());
                hitBox.setLocation(destination);
                hitBox.updateMovingTicks(moveSpeed);
                remove(hitBox, source);
                add(hitBox, destination);
            }

            return false;
        }
    }

    public Tile getTile(int r, int s, int z) {
        return columns[r][s].getTile(z);
    }

    public Column getColumn(int r, int s) { return columns[r][s]; }

    public void setColumn(int r, int s, Column c) { columns[r][s] = c; }


    @Override
    public boolean move(TakeableItem item, Location location, int movespeed) {
        Location source = item.getLocation();

        if(isOutOfBounds(location)){
            return false;
        }

        //checks to see if anything is blocking your height when moving
        CanMoveVisitor visitor = new TerranianCanMoveVisitor();
        boolean canMove = true;
        for(int i = 0; i < 1 && canMove; ++i){
            Tile tile = this.getTile(location.add(new Location(0,0,i)));
            tile.accept(visitor);
            canMove = canMove && visitor.canMove();
        }

        //checks the tile you will be standing on
        Tile tileBelow = this.getTile(location.subtract(new Location(0,0,1)));
        tileBelow.accept(visitor);
        canMove = canMove && visitor.CanMoveBelow();

        if(canMove) {//move if you can
            item.setLocation(location);
            remove(item, source);
            add(item, location);
            return true;
        }

        return false;
    }

    @Override
    public boolean fall(TakeableItem item, Location location) {
        CanFallVisitor visitor = new TerranianCanFallVisitor();
        getTile(location).accept(visitor);
        int tilesCount = 0;
        while(visitor.isCanMove()){
            location.setZ(location.getZ()-1);
            getTile(location).accept(visitor);
            tilesCount++;
        }
        if(tilesCount > 0) {
            location.setZ(location.getZ() + 1);
            return move(item, location, 2*tilesCount);
        }
        else{
            return false;
        }
    }

    @Override
    public boolean drop(TakeableItem item, Location location) {
        item.setLocation(location);
        item.setIsDestoryed(false);
        item.accept(voCreationVisitor);
        getTile(location).add(new TakeableMoveable(item.getName(), item.getValue(), item, this, location));
        return true;
    }
//    @Override
//    public void interact(Character interactor, Location destination) {
//        getTile(destination).interact(interactor);
//    }

    @Override
    public void interact(Avatar avatar, Location location) {
        getTile(location).interact(avatar);
    }

//    @Override
//    public void useAbility(ArrayList<Location> locations, StatsAddable effect){
//
//        for(Location location : locations){
//
//            getTile(location).add(effect);
//        }
//    }

    public Tile getTile(Location loc){
        return columns[loc.getR()][loc.getS()].getTile(loc.getZ());
    }

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitMap(this);
    }

    public boolean add(AreaOfEffect aoe, Location loc) {
        return columns[loc.getR()][loc.getS()].add(aoe, loc.getZ());
    }

    public boolean add(Entity entity, Location loc){
        entity.setLocation(loc);

        return columns[loc.getR()][loc.getS()].add(entity, loc.getZ());
    }
    public boolean add(OneShot oneShot, Location loc){
        oneShot.setLocation(loc);
        return columns[loc.getR()][loc.getS()].add(oneShot, loc.getZ());
    }
    public boolean add(TakeableItem takeableItem, Location loc){
        takeableItem.setLocation(loc);
        return columns[loc.getR()][loc.getS()].add(new TakeableMoveable(takeableItem.getName(), takeableItem.getValue(), takeableItem, this, loc), loc.getZ());
    }
    public boolean add(Obstacle obstacle, Location loc){
        obstacle.setLocation(loc);
        return columns[loc.getR()][loc.getS()].add(obstacle, loc.getZ());
    }
    public boolean add(InteractiveItem interactiveItem, Location loc){
        interactiveItem.setLocation(loc);
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
    public void death(Entity entity){
        remove(entity, entity.getLocation());

    }
//
//    @Override
//    public void interact(Character character, Location location) {
//
//    }


    public boolean add(HitBox hitbox, Location loc){
        if(columns[loc.getR()][loc.getS()].add(hitbox, loc.getZ())){
            hitbox.setLocation(loc);
            return true;
        }
        return false;
    }

    public boolean remove(HitBox hitbox, Location loc){
        return columns[loc.getR()][loc.getS()].remove(hitbox, loc.getZ());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
