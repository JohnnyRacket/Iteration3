package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.AvatarStates.AvatarState;
import com.wecanteven.Models.Entities.AvatarStates.EntityState;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Avatar extends Entity{
    Character avatar;
    AvatarState state;
    public Avatar(Character avatar, ActionHandler actionHandler){
        super(actionHandler, avatar.getDirection());
        this.avatar = avatar;
        state = new EntityState(avatar, this);
        //this.setMovingTicks(10);
    }
    public boolean move(Direction d){
        return state.move(d);
    }
    public void attack(Direction d){}
    public void useAbility(int index){}
    public boolean equipItem(EquipableItem item){
        return false;
    }
    public boolean unequipItem(EquipableItem item){
        return false;
    }
    public boolean equipAbility(String id){
        return false;
    }
    public boolean unequipAbility(String id){
        return false;
    }
    public boolean consume(String id){
        return false;
    }
    public void drop(){}
    public void pickup(){}
    public void interactWith(){}
    public void mount(){
    }
    public void dismount(){
    }

    @Override
    public void setLocation(Location location) {
        avatar.setLocation(location);
    }

    @Override
    public void setDirection(Direction direction) {
        avatar.setDirection(direction);
    }

    @Override
    public void setMovingTicks(int ticks) {
        avatar.setMovingTicks(ticks);
    }

    @Override
    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        avatar.setCanMoveVisitor(canMoveVisitor);
    }

    @Override
    public void setActionHandler(ActionHandler actionHandler) {
        avatar.setActionHandler(actionHandler);
    }



    @Override
    public ArrayList<Observer> getObservers() {
        return avatar.getObservers();
    }

    @Override
    public boolean isActive() {
        return avatar.isActive();
    }

    @Override
    public Location getLocation() {
        return avatar.getLocation();
    }

    @Override
    public int getMovingTicks() {
        return avatar.getMovingTicks();
    }

    @Override
    public void deIncrementTick() {
        avatar.deIncrementTick();
    }

    @Override
    public Direction getDirection() {
        return avatar.getDirection();
    }

    @Override
    public void levelUp() {
        avatar.levelUp();
    }

    @Override
    public Stats getStats() {
        return avatar.getStats();
    }

    @Override
    public void accept(EntityVisitor v) {
        avatar.accept(v);
    }

    @Override
    public CanMoveVisitor getCanMoveVisitor() {
        return avatar.getCanMoveVisitor();
    }

    @Override
    public ActionHandler getActionHandler() {
        return avatar.getActionHandler();
    }

    @Override
    public int getHeight() {
        return avatar.getHeight();
    }

    @Override
    public int getJumpHeight() {
        return avatar.getJumpHeight();
    }

    @Override
    public void setHeight(int height) {
        avatar.setHeight(height);
    }

    @Override
    public void setJumpHeight(int jumpHeight) {
        avatar.setJumpHeight(jumpHeight);
    }

    @Override
    public CanFallVisitor getCanFallVisitor() {
        return avatar.getCanFallVisitor();
    }

    @Override
    public void setCanFallVisitor(CanFallVisitor canFallVisitor) {
        avatar.setCanFallVisitor(canFallVisitor);
    }
}
