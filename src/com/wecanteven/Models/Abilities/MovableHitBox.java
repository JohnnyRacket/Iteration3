package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/11/2016.
 */
public class MovableHitBox extends HitBox implements Moveable, ViewObservable, Directional {
    private CanMoveVisitor canMoveVisitor;
    private int movingTicks;
    private boolean isActive;
    private Direction direction;
    private int speed,distance;
    private int count = 0;
    private boolean canMove = true;
    private ArrayList<Observer> observers;

    public MovableHitBox(String name, Location source, StatsAddable effect, ActionHandler actionHandler){
        super(name,source,effect,actionHandler);
        observers = new ArrayList<>();

        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setMovingTicks(0);
        setIsActive(false);
    }

    @Override
    public ArrayList<Observer> getObservers(){
        return observers;
    }

    public void addToMap(int distance, int speed, Direction direction){
        this.distance = distance;
        this.direction = direction;
        this.speed = speed;
        accept(getVoCreationVisitor());
        move(getLocation().add(direction.getCoords), speed);
    }
    private void move(Location destination,int speed){
        if(count >= distance && canMove){
            getActionHandler().remove(this,getLocation());
            return;
        }
        canMove = getActionHandler().move(this,destination,speed);
        count++;

    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor){
        this.canMoveVisitor = canMoveVisitor;
    }
    public CanMoveVisitor getCanMoveVisitor(){
        return canMoveVisitor;
    }

    private void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    public boolean isActive(){
        return isActive;
    }
    public void setMovingTicks(int movingTicks){
        setIsActive(true);
        this.movingTicks = movingTicks;
        notifyObservers();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getMovingTicks() {
        return movingTicks;
    }
    public void updateMovingTicks(int ticks) {
        setMovingTicks(calculateMovementTicks(ticks));
        calculateActiveStatus();
        tickTicks();
    }

    private void tickTicks(){
        if(isActive()){
            ModelTime.getInstance().registerAlertable(() -> {
                deIncrementMovingTick();
                calculateActiveStatus();
                tickTicks();
            }, 1);
        }
    }
    private void deIncrementMovingTick(){
        movingTicks--;
    }
    private int calculateMovementTicks(int movementSpeed){
        return (int) ((30D/movementSpeed)*10);
    }

    private void calculateActiveStatus(){
        if(getMovingTicks() <= 0){
            setIsActive(false);
            move(getLocation().add(direction.getCoords),speed);
        }
        else{
            setIsActive(true);
        }
    }
    @Override
    public void accept(VOCreationVisitor visitor) {
        visitor.visitMovableHitBox(this);
    }

}
