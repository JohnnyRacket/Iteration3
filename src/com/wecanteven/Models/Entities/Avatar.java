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
public class Avatar{
    Character avatar;
    AvatarState state;
    public Avatar(Character avatar, ActionHandler actionHandler){
        this.avatar = avatar;
        state = new EntityState(avatar, this);
        //this.setMovingTicks(10);
    }
    public boolean move(Direction d){
        return state.move(d);
    }
    public void attack(Direction d){ state.attack(d);}
    public void useAbility(int index){state.useAbility(index);}
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



}
