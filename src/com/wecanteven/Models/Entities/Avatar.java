package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.AvatarStates.AvatarState;
import com.wecanteven.Models.Entities.AvatarStates.DeadState;
import com.wecanteven.Models.Entities.AvatarStates.EntityState;
import com.wecanteven.Models.Entities.AvatarStates.MountState;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.AvatarVisitor;
import com.wecanteven.Visitors.CanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.ArrayList;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Avatar implements Observer{
    Character avatar;
    AvatarState state;
    Map map;
    public Avatar(Character avatar, Map map){
        this.avatar = avatar;
        this.map = map;
        avatar.modelAttach(this);
        state = new EntityState(avatar, this);
    }
    public boolean move(Direction d){
        return state.move(d);
    }
    public void attack(){ state.attack();}
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
    public void interactWithTile(){
        getCharacter().interact(this);
    }
    public void mount(Mount mount){
        swapState(new MountState(avatar, mount));
    }
    public void dismount(){
    }

    public Character getCharacter(){
        return avatar;
    }
    public void accept(AvatarVisitor visitor){
        visitor.visitAvatar(this);
    }

    @Override
    public void update() {
        if (avatar.isDestroyed()) {
            this.state = new DeadState();
            ModelTime.getInstance().registerAlertable( () -> {
                avatar.setDestroyed(false);
                map.add(avatar, new Location(13,13,19));
                this.state = new EntityState(avatar, this);
            }, 45);
        }
    }

    protected void swapState(AvatarState state) {
        this.state = state;
    }
}
