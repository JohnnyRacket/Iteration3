package com.wecanteven.Models.ModelTime;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class ModelTime implements Tickable {

    private boolean paused = false;
    private List<TimedObject> alertables;
    private List<Tickable> tickables;
    private List<Tickable> unstoppables;

    private static ModelTime ourInstance = new ModelTime();

    public static ModelTime getInstance() {
        return ourInstance;
    }

    private ModelTime() {
        alertables = new CopyOnWriteArrayList<>();
        tickables = new CopyOnWriteArrayList<>();
        unstoppables = new CopyOnWriteArrayList<>();
    }

    @Override
    public void tick() {
        if(!paused) {
            for (Iterator<TimedObject> iterator = alertables.iterator(); iterator.hasNext(); ) {
                TimedObject object = iterator.next();
                if (object.decrement()) {// if its time to execute the object
                    alertables.remove(object);
                }
            }
            for (Iterator<Tickable> iterator = tickables.iterator(); iterator.hasNext(); ) {
                Tickable object = iterator.next();
                object.tick();
            }
        }
        for (Iterator<Tickable> iterator = unstoppables.iterator(); iterator.hasNext(); ) {
            Tickable object = iterator.next();
            object.tick();
        }
    }

    public void registerAlertable(Alertable alertable, int ticks){
        alertables.add(new TimedObject(alertable, ticks));
    }
    public void registerTickable(Tickable tickable){tickables.add(tickable);}
    public void removeTickable(Tickable tickable){tickables.remove(tickable);}

    public void registerUnstoppable(Tickable tickable){unstoppables.add(tickable);}
    public void removeUnstoppable(Tickable tickable){unstoppables.remove(tickable);}

    public void pause(){

        paused = true;
    }
    public void resume(){

        paused = false;
    }
    public void reset(){
        alertables = new CopyOnWriteArrayList<>();
        tickables = new CopyOnWriteArrayList<>();
    }
}
