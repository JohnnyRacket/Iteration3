package com.wecanteven.Models.Abilities;

import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
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

    public MovableHitBox(String name, Location source, StatsAddable effect){
        super(name,source,effect);
        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setMovingTicks(0);
        setIsActive(false);
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
        this.movingTicks = movingTicks;
    }
    public int getMovingTicks() {
        return movingTicks;
    }
    public void updateMovingTicks(int ticks) {
        setMovingTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        //notifyObservers();
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
    private int calculateMovementTicks(int movementStat){
        return (int) ((30D/movementStat)*10D);
    }

    private void calculateActiveStatus(){
        if(getMovingTicks() <= 0){
            setIsActive(false);
        }
        else{
            setIsActive(true);
        }
    }
}
