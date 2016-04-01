package com.wecanteven.Models.Entities;

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

public class Entity implements Moveable, Directional, Observable{
    ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    private Location location;
    private CanMoveVisitor canMoveVisitor;
    protected Stats stats;

    public Entity(){}
    public boolean move(Direction d){
        return false;
    }
    public void die(){}
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

    private void levelUp(){
        stats.levelUp(new StatsAddable(1,1,1,1,1,1,1,1,1));
    }

    public void accept(EntityVisitor v) {
        v.visitEntity(this);
    }
}
