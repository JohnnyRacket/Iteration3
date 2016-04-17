package com.wecanteven.Controllers.AIControllers;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.ModelTime.Tickable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 4/16/2016.
 */
public class AITime implements Tickable{

   // private Map map;
    private ArrayList<Tickable> aiControllers;
    private boolean paused = false;

    private static AITime ourInstance = new AITime();
    public static AITime getInstance() {
        return ourInstance;
    }

    private AITime() {
        aiControllers = new ArrayList<>();
    }


    @Override
    public void tick() {
        if(!paused) {
            for (Iterator<Tickable> iterator = aiControllers.iterator(); iterator.hasNext(); ) {
                Tickable object = iterator.next();
                object.tick();
            }
        }
    }
    public void pause(){
        paused = true;
    }
    public void resume(){ paused = false; }
    public void registerController(Tickable tickable){aiControllers.add(tickable);}
    public void removeController(Tickable tickable){aiControllers.remove(tickable);}
}
