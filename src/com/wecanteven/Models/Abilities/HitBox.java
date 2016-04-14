package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/11/2016.
 */
public class HitBox {
    private String name;
    private StatsAddable effect;
    private Location location;
    private ActionHandler actionHandler;
    static VOCreationVisitor voCreationVisitor;
    public HitBox(String name,Location location, StatsAddable effect, ActionHandler actionHandler){
        setName(name);
        setLocation(location);
        setEffect(effect);
        setActionHandler(actionHandler);
    }

    public void addToMap(int duration, Location destination){
        HitBox hitBox = this;
        actionHandler.add(hitBox,destination);
        setLocation(destination);
        accept(voCreationVisitor);
        ModelTime modelTime = ModelTime.getInstance();
        modelTime.registerAlertable(new Alertable() {
            @Override
            public void alert() {
                actionHandler.remove(hitBox, destination);
            }
        }, 1);
    }

    public void interact(Entity entity){
        entity.modifyStats(effect);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public void setEffect(StatsAddable effect){
        this.effect = effect;
    }
    public void setActionHandler(ActionHandler actionHandler){
        this.actionHandler = actionHandler;
    }

    public String getName(){
        return name;
    }
    public Location getLocation(){
        return location;
    }
    public StatsAddable getEffect(){
        return effect;
    }
    public ActionHandler getActionHandler(){
        return actionHandler;
    }
    static public void setVOCreationVisitor(VOCreationVisitor visitor){
        voCreationVisitor = visitor;
    }
    public void accept(VOCreationVisitor visitor) {
        visitor.visitHitBox(this);
    }

}
