package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.BuffManager.BuffManager;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.*;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.*;
import com.wecanteven.Visitors.CanFallVisitors.CanFallVisitor;
import com.wecanteven.Visitors.CanFallVisitors.FlyingCanFallVisitor;
import com.wecanteven.Visitors.CanFallVisitors.TerranianCanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.TerranianCanMoveVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Entity implements Moveable, Directional,Destroyable, ModelObservable, ViewObservable, Observer{
    private ArrayList<Observer> observers = new ArrayList<>();
    private ArrayList<Observer> modelObservers = new ArrayList<>();

    @Override
    public ArrayList<Observer> getModelObservers() {
        return modelObservers;
    }

    public void setModelObservers(ArrayList<Observer> modelObservers) {
        this.modelObservers = modelObservers;
    }

    private ActionHandler actionHandler;
    private Stats stats;
    private int height, jumpHeight;
    private Location location;
    private Direction direction;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;
    private int movingTicks, turningTicks;
    private boolean lock, isActive;
    private BuffManager buffmanager;

    private CanFallVisitor cachedCanFall;

    private GameColor color;

    private boolean isDestroyed = false;

    public Entity(ActionHandler actionHandler, Direction direction, GameColor color){
        setStats(new Stats(this));
        setHeight(3);
        setJumpHeight(25);

        setActionHandler(actionHandler);
        setDirection(direction);
        setCanMoveVisitor(new TerranianCanMoveVisitor());
        setCanFallVisitor(new TerranianCanFallVisitor());
        setMovingTicks(0);
        setIsActive(false);
        this.color = color;

        buffmanager = new BuffManager(this);
    }

    public GameColor getColor() {
        return color;
    }

    public BuffManager getBuffmanager() {
        return buffmanager;
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
    public int getTurningTicks() {
        return turningTicks;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void update(){
        loseLife();
    }

    @Override
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
            updateTurningTicks(5);
            return false;
        }
    }

    public boolean jump(Direction d){
        int movementStat = getStats().getMovement();
        if(movementStat == 0 || isActive()){
            return false;
        }
        Location destination = getLocation().add(d.getCoords);
        int moveTime = calculateMovementTicks(movementStat);
        return getActionHandler().move(this,destination,moveTime);
    }

    @Override
    public boolean move(Location l) {
        int movementStat = getStats().getMovement();
        if(movementStat == 0 || isActive()){
            return false;
        }

        int moveTime = calculateMovementTicks(movementStat);
        return getActionHandler().move(this,l,moveTime);
    }

    @Override
    public boolean fall(){
        if(!isActive()) {
            Location tileBelow = getLocation().subtract(new Location(0, 0, 1));
            return getActionHandler().fall(this, tileBelow);
        }
        return false;
    }

    public void killEntity(){
        getStats().loseLife();
    }

    public void loseLife(){
        //setLocation(new Location(3, 9, 1));
        isDestroyed = true;
        notifyObservers();
        modelNotifyObservers();
        getActionHandler().death(this);
        //getStats().refreshStats();
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
    public void updateTurningTicks(int ticks) {
        setTurningTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        notifyObservers();
    }
    public void setMovingTicks(int movingTicks){
        this.movingTicks = movingTicks;
    }
    public void setTurningTicks(int turningTicks){
        this.turningTicks = turningTicks;
    }

    protected void tickTicks(){
        if(isActive()){
            ModelTime.getInstance().registerAlertable(() -> {
                deIncrementMovingTick();
                deIncrementTurningTick();
                calculateActiveStatus();
                tickTicks();
            }, 1);
        }
    }

    protected void deIncrementMovingTick(){
        if(getMovingTicks()>0)
        movingTicks--;
    }
    protected void deIncrementTurningTick(){
        if(getTurningTicks()>0)
            turningTicks--;
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



    public void notifyObserversOnNotDestroyed() {
        if(!isDestroyed())
            notifyObservers();
    }

    public void levelUp(){
        getStats().levelUp();
    }

    public StatsAddable getLevelUpStats(){
        return new StatsAddable(0, 1, 1, 1, 1, 0, 0, 0, 0);
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

    public boolean calculateActiveStatus(){
        if(getMovingTicks() <= 0 && getMovingTicks() <= 0){
            setIsActive(false);
            return false;
        }
        else{
            setIsActive(true);
            return true;
        }
    }

    public void setIsActive(boolean isActive){
        if(!isLocked()){
            this.isActive = isActive;
        }
    }

    public void modifyStatsAdditive(StatsAddable addable){

        this.stats.addStats(addable);
    }

    public void modifyStatsSubtractive(StatsAddable addable){

        this.stats.subtractStats(addable);
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
        notifyObservers();
    }

    public void takeDamage(int dmgAmount) {
        getStats().takeDamage(dmgAmount);
    }

    public void healDamage(int healAmount) {
        getStats().healDamage(healAmount);
    }

    public void buff(Buff buff) {
        buffmanager.addBuff(buff);
    }

    public void pauseBuffs() {
        getBuffmanager().disable();
    }

    public void resumeBuffs() {
        getBuffmanager().enable();
    }

    public void interact(Character character) {}

    public void interact() {}

    public void cacheCanFall() {
        cachedCanFall = getCanFallVisitor();
    }

    public void restoreCanFall() {
        setCanFallVisitor(cachedCanFall);
    }

    public void setColor(GameColor color){
        this.color = color;
    }
}
