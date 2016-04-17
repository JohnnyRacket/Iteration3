package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by Brandon on 4/11/2016.
 */
public class HitBox implements Destroyable{
    private String name;
    private Effects effect;
    private Location location;
    private ActionHandler actionHandler;
    static VOCreationVisitor voCreationVisitor;
    private boolean isDestroyed;
    private int duration;

    public HitBox(String name,Location location, Effects effect, ActionHandler actionHandler,int duration){
        setDuration(duration);
        setName(name);
        setLocation(location);
        setEffects(effect);
        setActionHandler(actionHandler);
        setIsDestroyed(false);
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
                setIsDestroyed(true);
            }
        }, duration);
    }

    public void interact(Entity entity){
        effect.interact(entity);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public void setEffects(Effects effect){
        this.effect = effect;
    }
    public void setActionHandler(ActionHandler actionHandler){
        this.actionHandler = actionHandler;
    }
    public void setIsDestroyed(boolean destroyed){
        isDestroyed = destroyed;
    }
    static public void setVOCreationVisitor(VOCreationVisitor visitor){
        voCreationVisitor = visitor;
    }

    public String getName(){
        return name;
    }
    public Location getLocation(){
        return location;
    }
    public Effects getEffect(){
        return effect;
    }
    public ActionHandler getActionHandler(){
        return actionHandler;
    }

    public VOCreationVisitor getVoCreationVisitor(){
        return voCreationVisitor;
    }
    public void accept(VOCreationVisitor visitor) {
        visitor.visitHitBox(this);
    }
    public boolean isDestroyed(){
        return isDestroyed;
    }
    public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
}
