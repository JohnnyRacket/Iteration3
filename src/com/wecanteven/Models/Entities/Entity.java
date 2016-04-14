package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.*;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.*;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */

public class Entity implements Moveable, Directional,Destroyable, ViewObservable, Observer{
    private ArrayList<Observer> observers;
    private ActionHandler actionHandler;
    private Stats stats;
    private int height, jumpHeight;
    private Location location;
    private Direction direction;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;
    private int movingTicks;
    private boolean lock, isActive;

    private boolean isDestroyed = false;

    public Entity(ActionHandler actionHandler, Direction direction){
        observers = new ArrayList<>();

        setStats(new Stats(this));
        setHeight(3);
        setJumpHeight(25);

        setActionHandler(actionHandler);
        setDirection(direction);
        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setCanFallVisitor(new TerranianCanFallVisitor());
        setMovingTicks(0);
        setIsActive(false);
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getMovingTicks() {
        return movingTicks;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void update(){
        System.out.println("crazy shit is happening");
        loseLife();
    }

    public boolean move(Direction d){
        int movementStat = getStats().getMovement();
        if(movementStat == 0 || isActive()){
            return false;
        }
        if(getDirection() == d){
            setDirection(d);
            Location destination = getLocation().add(d.getCoords);
            int moveTime = calculateMovementTicks(movementStat);
            return getActionHandler().move(this,destination,moveTime);
        }else{
            setDirection(d);
            return false;
        }
    }

    public boolean move(Location l) {
        int movementStat = getStats().getMovement();
        if(movementStat == 0 || isActive()){
            return false;
        }

        int moveTime = calculateMovementTicks(movementStat);
        return getActionHandler().move(this,l,moveTime);
    }

    public boolean fall(){
        if(!isActive()) {
            Location tileBelow = getLocation().subtract(new Location(0, 0, 1));
            return getActionHandler().fall(this, tileBelow);
        }
        return false;
    }

    public void loseLife(){
        getActionHandler().death(this);
        setLocation(new Location(3, 9, 1));
        notifyObservers();
        getStats().refreshStats();
    }


    public boolean isActive(){
        return isActive;
    }



    public void updateMovingTicks(int ticks) {
        setMovingTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        notifyObservers();
    }

    public void setMovingTicks(int movingTicks){
        this.movingTicks = movingTicks;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
        notifyObservers();
    }



    public void setLocation(Location location) {
        System.out.println("Im @ " + location);
        this.location = location;
        notifyObservers();
    }

    public void levelUp(){
        getStats().addStats(new StatsAddable(0, 1, 1, 1, 1, 0, 0, 0, 0));
    }


    public Stats getStats(){
        return stats;
    }
    public void setStats(Stats stats){
        this.stats = stats;
    }

    public void accept(EntityVisitor v) {
        v.visitEntity(this);
    }

    public CanMoveVisitor getCanMoveVisitor() {
        return canMoveVisitor;
    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        this.canMoveVisitor = canMoveVisitor;
        this.canMoveVisitor.setEntity(this);
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
        calculateActiveStatus();
    }
    private void calculateActiveStatus(){
        if(getMovingTicks() <= 0){
            setIsActive(false);
        }
        else{
            setIsActive(true);
        }
    }

    protected void setIsActive(boolean isActive){
        if(!isLocked()){
            this.isActive = isActive;
        }
    }

    public void modifyStats(StatsAddable addable){
        System.out.println("The Entity's stats have changed");
        this.stats.addStats(addable);
    }


    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void takeDamage(int dmgAmount) {
        getStats().takeDamage(dmgAmount);
    }

    public void healDamage(int healAmount) {
        getStats().healDamage(healAmount);
    }

//    public void loseLife() {
//        System.out.println("Entity Lost a life");
//        getStats().loseLife();
//        isDestroyed = true;
//        ForDeath();
//        notifyObservers();
//    }
}
