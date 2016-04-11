package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class EntityState extends AvatarState {
    private Character avatar;
    private Avatar controller;
    public EntityState(Character avatar, Avatar controller){
        this.avatar = avatar;
        this.controller = controller;
    }
    public boolean move(Direction d){
       return avatar.move(d);
    }
    public void attack(){
        avatar.attack();
    }
    public void useAbility(int index){
        avatar.useAbility(index);
    }
    public boolean equipItem(String id){
        return false;
    }
    public boolean unequipItem(String id){
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
        controller.mount();
    }
    public void dismount(){}
}
