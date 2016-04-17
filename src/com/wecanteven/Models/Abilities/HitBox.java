package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Abilities.Effects.Effects;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;
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

    public void addToMap(int removeTime, Location destination){
        HitBox hitBox = this;
        getActionHandler().add(hitBox, destination);
        setLocation(destination);
        ViewTime viewTime = ViewTime.getInstance();
        viewTime.register(new ViewTime.vCommand() {
            @Override
            public void execute() {
                accept(voCreationVisitor);
            }
        },0);
                       // accept(voCreationVisitor);

        ModelTime modelTime = ModelTime.getInstance();
        modelTime.registerAlertable(new Alertable() {
            @Override
            public void alert() {
                getActionHandler().remove(hitBox, destination);
                setIsDestroyed(true);
            }
        }, removeTime);
    }

    public void interact(Entity entity){
        effect.interact(entity);
    }

    public void setDuration(int duration){
        this.duration = duration;
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

    public int getDuration(){
        return duration;
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
    public boolean isDestroyed(){
        return isDestroyed;
    }
    public VOCreationVisitor getVoCreationVisitor(){
        return voCreationVisitor;
    }

    public void accept(VOCreationVisitor visitor) {
        visitor.visitHitBox(this);
    }
}
