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

        //avatar.move(mount.getLocation());
        //avatar.setCanMoveVisitor(null);
        avatar.setMovingTicks(mount.getMovingTicks());
        avatar.setDirection(mount.getDirection());
        avatar.setLocation(new Location(mount.getLocation().getR(), mount.getLocation().getS(), mount.getLocation().getZ() + 2));
        //updatePlayerLocation();
        //System.out.println("Avatar Location:" + avatar.getLocation());
        return moved;
    }
    public boolean jump(Direction d){
        boolean jumped = true;
        jumped = mount.jump(d) && jumped;

        //avatar.jump(mount.getLocation());
        avatar.setMovingTicks(mount.getMovingTicks());
        avatar.setLocation(mount.getLocation());
        //updatePlayerLocation();
        System.out.println("Avatar Location:" + avatar.getLocation());
        return jumped;
    }

    private void updatePlayerLocation() {
        Location avatarLocation = avatar.getLocation();
        avatarLocation.setR(mount.getLocation().getR());
        avatarLocation.setS(mount.getLocation().getS());
        avatarLocation.setZ(mount.getLocation().getZ());
    }
    public void attack(Direction dir){}
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
