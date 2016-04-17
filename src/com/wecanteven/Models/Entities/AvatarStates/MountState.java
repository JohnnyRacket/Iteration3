package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.AreaView.Position;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

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
        moved = mount.move(d) && moved;

        avatar.move(mount.getLocation());
        //avatar.setLocation(mount.getLocation());
        //updatePlayerLocation();
        System.out.println("Avatar Location:" + avatar.getLocation());
        return moved;
    }

    private void updatePlayerLocation() {
        Location avatarLocation = avatar.getLocation();
        avatarLocation.setR(mount.getLocation().getR());
        avatarLocation.setS(mount.getLocation().getS());
        avatarLocation.setZ(mount.getLocation().getZ());
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
    public void mount(Character mounter){
        System.out.println("Calling mount on the Mount Object");
        mount.mount(mounter);
    }
    public void dismount(){
        mount.dismount();
    }
}
