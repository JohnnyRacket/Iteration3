package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.AvatarStates.AvatarState;
import com.wecanteven.Models.Entities.AvatarStates.EntityState;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

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
        super.setLocation(location);
        avatar.setLocation(location);
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
        avatar.setDirection(direction);
    }
}
