package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
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
    protected Stats stats;
    private int height = 3;
    private int jumpHeight;
    protected Location location;
    private Direction direction;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;
    private int movingTicks;
    private boolean isActive;
    private boolean lock;


    public Entity(ActionHandler actionHandler, Direction direction){
        this.actionHandler = actionHandler;
        this.direction = direction;
        canMoveVisitor = new TerranianCanMoveVisitor();
        canMoveVisitor.setEntity(this);
        canFallVisitor = new TerranianCanFallVisitor();
        jumpHeight = 2;
        movingTicks = 0;
        isActive = false;
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    public void update(){
        die();
    }



    public boolean move(Direction d){
        int movementStat = this.getStats().getMovement();
        if(movementStat == 0 || isActive){
            return false;
        }
        if(this.getDirection() == d){
            setDirection(d);
            setMovingTicks(calculateMovementTicks(movementStat));
            Location destination = location.add(d.getCoords);
            return actionHandler.move(this,destination);
        }else{
            this.setDirection(d);
            return false;
        }
    }

    public boolean fall(){
        if(!isActive()) {
            if (location.getZ() == 1) {
                return false;
            }
            setMovingTicks(3);
            return actionHandler.fall(this, this.getLocation().subtract(new Location(0, 0, 1)));
        }
        return false;
    }


    public void die(){
        stats.refreshStats();
    }
    public boolean isActive(){
        return isActive;
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

    public void setMovingTicks(int ticks) {
        this.movingTicks = ticks;
        if(ticks == 0){
            setIsActive(false);
            return;
        }
        setIsActive(true);
        deIncrementTick();
        notifyObservers();
    }

    public int deIncrementTick(){
        ModelTime.getInstance().registerAlertable(() -> {
                if(movingTicks == 0){
                    setIsActive(false);
                    return;
                }
                movingTicks--;
                deIncrementTick();
        }, 1);
        return movingTicks;
    }

    private int calculateMovementTicks(int movementStat){
        return (movementStat/30)*10;
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
        this.location = location;
        notifyObservers();
    }

    public void levelUp(){
        stats.addStats(new StatsAddable(0,1,1,1,1,0,0,0,0));
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

    public boolean isLocked() {
        return lock;
    }

    public void lock() {
        lock = true;

    }
    public void unlock(){
        lock = false;
        if(getMovingTicks() <= 0){
            setIsActive(false);
        }
        else{
            setIsActive(true);
        }
    }
    private void setIsActive(boolean isActive){
        if(!isLocked()){
            this.isActive = isActive;
        }
    }
}
