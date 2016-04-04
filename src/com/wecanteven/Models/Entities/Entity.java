package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.*;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */

public class Entity implements Moveable, Directional, ViewObservable, Observer{
    ArrayList<Observer> observers = new ArrayList<>();
    private ActionHandler actionHandler;
    private int movingTicks = 20;
    private int height = 3;
    private Direction direction;
    private int jumpHeight;
    protected Location location;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;
    protected Stats stats;

    public Entity(ActionHandler actionHandler, Direction direction){
        this.actionHandler = actionHandler;
        this.direction = direction;
        canMoveVisitor = new TerranianCanMoveVisitor();
        canFallVisitor = new TerranianCanFallVisitor();
        jumpHeight = 15;
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    public void update(){
        die();
    }



    public boolean move(Direction d){
        setDirection(d);
        Location destination = location.add(d.getCoords);
        return moveHelper(destination);
    }
    private boolean moveHelper(Location destination){
        if(actionHandler.move(this,destination)){
            return true;
        }
        else if(location.getZ()+jumpHeight != destination.getZ()) { //checks to see if the entity has tried to step up
            System.out.println("Checks if it could step up");
            return moveHelper(destination.add(Direction.UP.getCoords));
        }
        return false;
    }
    public boolean fall(){
        if(location.getZ() == 1){
            return false;
        }
        System.out.println("Falling");
        return actionHandler.fall(this, this.getLocation().subtract(new Location(0,0,1)));
    }
    public void die(){
        stats.refreshStats();
    }
    public boolean isActive(){
        return false;
    }





    //TODO: someone write the below shit
    @Override

    public Location getLocation() {
        return location;
    }


    @Override
    public int getMovingTicks() {
        return movingTicks;
    }

    //Alex's testing code
    public void setMovingTicks(int ticks) {
        this.movingTicks = ticks;
        notifyObservers();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        notifyObservers();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setLocation(Location location) {
        System.out.println("Location was changed");
        this.location = location;
        notifyObservers();
    }

    public void levelUp(){
        stats.modifyStats(new StatsAddable(0,1,1,1,1,0,0,0,0));
    }

    public Stats getStats(){
        return stats;
    }

    public void accept(EntityVisitor v) {
        v.visitEntity(this);
    }

    public CanMoveVisitor getCanMoveVisitor() {
        return canMoveVisitor;
    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        this.canMoveVisitor = canMoveVisitor;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public CanFallVisitor getCanFallVisitor() {
        return canFallVisitor;
    }

    public void setCanFallVisitor(CanFallVisitor canFallVisitor) {
        this.canFallVisitor = canFallVisitor;
    }
}
