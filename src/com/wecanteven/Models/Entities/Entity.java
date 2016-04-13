package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
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
    private Stats stats;
    private int height = 3;
    private int jumpHeight;
    private Location location;
    private Direction direction;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;
    private int movingTicks;
    private boolean isActive;
    private boolean lock;

    public Entity(ActionHandler actionHandler, Direction direction){
        this.actionHandler = actionHandler;
        this.direction = direction;
        stats = new Stats(this);
        canMoveVisitor = new TerranianCanMoveVisitor();
        canMoveVisitor.setEntity(this);
        canFallVisitor = new TerranianCanFallVisitor();
        jumpHeight = 25;
        movingTicks = 0;
        isActive = false;
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
        checkForDeath();
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
            if (getLocation().getZ() == 1) {
                return false;
            }
            Location tileBelow = getLocation().subtract(new Location(0, 0, 1));
            return getActionHandler().fall(this, tileBelow);
        }
        return false;
    }

    public void checkForDeath(){
        getStats().refreshStats();
        if(getStats().getLives() <= 0){
            die();
        }
    }

    public void die() {
        System.out.println("The entity has died");
        getActionHandler().death(this);
        notifyObservers();
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
        this.location = location;
        notifyObservers();
    }

    public void levelUp(){
        stats.addStats(new StatsAddable(0,1,1,1,1,0,0,0,0));
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

    public void takeDamage(int dmgAmount) {
        getStats().takeDamage(dmgAmount);
    }

    public void healDamage(int healAmount) {
        getStats().healDamage(healAmount);
    }

    public void loseLife() {
        getStats().loseLife();

        checkForDeath();
    }
}
