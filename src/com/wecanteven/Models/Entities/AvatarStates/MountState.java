package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.AreaView.Position;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Observers.ModelObservable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanFallVisitors.CanFallVisitor;
import com.wecanteven.Visitors.CanFallVisitors.FlyingCanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class MountState extends AvatarState implements Observer {
    private Character avatar;
    private Mount mount;
    private Avatar controller;

    public MountState(Character avatar, Mount mount, Avatar controller) {
        this.avatar = avatar;
        this.mount = mount;
        this.controller = controller;
        mount.modelAttach(this);
        mount(controller);
        FlyingCanFallVisitor flyingCanFallVisitor = new FlyingCanFallVisitor();
        mount.setCanFallVisitor(flyingCanFallVisitor);
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
        //
        return moved;
    }
    public boolean jump(Direction d){
        boolean jumped = true;
        jumped = mount.jump(d) && jumped;

        //avatar.jump(mount.getLocation());
        avatar.setMovingTicks(mount.getMovingTicks());
        avatar.setLocation(new Location(mount.getLocation().getR(), mount.getLocation().getS(), mount.getLocation().getZ() + 2));
        //updatePlayerLocation();

        return jumped;
    }

    public boolean fall(Direction d){
        boolean jumped = true;
        jumped = mount.jump(d) && jumped;

        //avatar.jump(mount.getLocation());
        avatar.setMovingTicks(mount.getMovingTicks());
        avatar.setLocation(new Location(mount.getLocation().getR(), mount.getLocation().getS(), mount.getLocation().getZ() + 2));
        //updatePlayerLocation();

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
    public void mount(Avatar mounter){
        //avatar.setCanFallVisitor(mount.getCachedCanFall());
        mount.mount(mounter);
    }
    public void dismount(){
        mount.dismount();
    }

    @Override
    public void update() {
        dismount();
        mount.modelDettach(this);
        EntityState entityState = new EntityState(avatar, controller);
        controller.swapState(entityState);
        avatar.fall();
    }
}
