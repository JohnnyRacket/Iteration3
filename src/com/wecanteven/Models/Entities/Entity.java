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
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */

public class Entity implements Moveable, Directional, ViewObservable, Observer{
    ArrayList<Observer> observers = new ArrayList<>();
    public ActionHandler actionHandler;
    private int movingTicks = 20;
    private int height = 3;
    private Direction direction;

    public Entity(ActionHandler actionHandler, Direction direction){
        this.actionHandler = actionHandler;
        this.direction = direction;
        canMoveVisitor = new TerranianCanMoveVisitor();
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    public void update(){
        die();
    }

    protected Location location;
    private CanMoveVisitor canMoveVisitor;
    protected Stats stats;

    public boolean move(Direction d){
        setDirection(d);
        return actionHandler.move(this, d.getCoords);
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
}
