package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Observers.*;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.TerranianCanMoveVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 4/11/2016.
 */
public class MovableHitBox extends HitBox implements Moveable, ViewObservable, Directional, Destroyable {
    private CanMoveVisitor canMoveVisitor;
    private int movingTicks;
    private boolean isActive;
    private int count;
    private boolean canMove;
    private ArrayList<Observer> observers;
    private int speed,distance;

    private Direction direction;

    public MovableHitBox(String name, Location location, Effects effect, ActionHandler actionHandler){
        super(name, location, effect, actionHandler, 0);
        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setMovingTicks(0);
        setIsActive(false);
        setCount(0);
        setCanMove(true);
        setIsDestroyed(false);
        observers = new ArrayList<>();
    }

    @Override
    public ArrayList<Observer> getObservers(){
        return observers;
    }

    public void addToMap(int distance, int speed, Direction direction){
        this.distance = distance;
        this.direction = direction;
        this.speed = speed;
        ViewTime viewTime = ViewTime.getInstance();
        viewTime.register(new ViewTime.vCommand() {
            @Override
            public void execute() {
                accept(voCreationVisitor);
            }
        },10);
        move(getLocation(), speed);
    }
    private void move(Location destination,int speed){
        if(count >= distance || !canMove){
            getActionHandler().remove(this,getLocation());
            setIsDestroyed(true);
            notifyObservers();
            return;
        }
        canMove = getActionHandler().move(this,destination,speed);
        count++;
    }
    public void updateMovingTicks(int ticks) {
        setMovingTicks(calculateMovementTicks(ticks));
        notifyObservers();
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


    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor){
        this.canMoveVisitor = canMoveVisitor;
    }
    public void setMovingTicks(int movingTicks){
        setIsActive(true);
        this.movingTicks = movingTicks;
    }
    private void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public CanMoveVisitor getCanMoveVisitor(){
        return canMoveVisitor;
    }
    @Override
    public int getMovingTicks() {
        return movingTicks;
    }
    public boolean isActive(){
        return isActive;
    }
    public int getCount(){
        return count;
    }
    public boolean getCanMove(){
        return canMove;
    }


    @Override
    public void setDirection(Direction d) {
        direction = d;
    }
    @Override
    public Direction getDirection() {
        return direction;
    }
    @Override
    public boolean move(Direction d) {
        return false;
    }
    @Override
    public boolean move(Location l) {
        return false;
    }
    @Override
    public boolean fall() {
        return false;
    }
}
