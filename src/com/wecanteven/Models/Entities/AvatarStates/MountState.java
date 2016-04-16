package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Brandon on 3/31/2016.
 */
public class MountState extends AvatarState {
    private Character avatar;
    private Mount mount;

    public MountState(Character avatar, Mount mount) {
        this.avatar = avatar;
        this.mount = mount;
        mount(avatar);
    }
    public boolean move(Direction d){
        boolean moved = true;
        //moved = avatar.move(d) && moved;
        moved = mount.move(d) && moved;
        return moved;
    }
    public void attack(){}
    public void useAbility(int index){}
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
    public void mount(Character mountee){
        mount.mount(mountee);
    }
    public void dismount(){
        mount.dismount();
    }
}
