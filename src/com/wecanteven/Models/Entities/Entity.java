package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */

public class Entity implements Moveable, Directional, Observable, Observer{
    ArrayList<Observer> observers = new ArrayList<>();
    ActionHandler actionHandler;

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    public void update(){
        die();
    }

    private Location location;
    private CanMoveVisitor canMoveVisitor;
    protected Stats stats;

    public Entity(){}
    public boolean move(Direction d){
        return actionHandler.move(this, d);
    }
    public void die(){
        stats.refreshStats();
    }
    public boolean isActive(){
        return false;
    }



    private int movingTicks = 0;
    private Direction direction;

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
}
