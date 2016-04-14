package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by Brandon on 4/11/2016.
 */
public class MovableHitBox extends HitBox{
    private CanMoveVisitor canMoveVisitor;
    private int movingTicks;
    private boolean isActive;
    private Direction direction;
    private int speed,distance;
    private int count = 0;
    private boolean canMove = true;

    public MovableHitBox(String name, Location source, StatsAddable effect, ActionHandler actionHandler){
        super(name,source,effect,actionHandler);
        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setMovingTicks(0);
        setIsActive(false);
    }

    public void addToMap(int distance, int speed, Direction direction){
        this.distance = distance;
        this.direction = direction;
        this.speed = speed;
        move(getLocation().add(direction.getCoords),speed);
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
        System.out.println("the moving ticks were set to: " + movingTicks);
        setIsActive(true);
        this.movingTicks = movingTicks;
    }
    public int getMovingTicks() {
        return movingTicks;
    }
    public void updateMovingTicks(int ticks) {
        setMovingTicks(calculateMovementTicks(ticks));
        calculateActiveStatus();
        tickTicks();
        //notifyObservers();
    }

    private void tickTicks(){
        if(isActive()){
            ModelTime.getInstance().registerAlertable(() -> {
                System.out.println("the projectile is on the move");
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
}
