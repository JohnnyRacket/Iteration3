package com.wecanteven.Controllers.AIControllers;

import com.wecanteven.Controllers.AIControllers.ActionControllers.AbstractActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.AbstractSearchingController;
import com.wecanteven.Controllers.AIControllers.Targets.Target;
import com.wecanteven.Models.ModelTime.Tickable;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 4/5/2016.
 */
public class AIController implements Tickable {

    private int reactionTime = 30;
    private int ticks;
    private AbstractActionController actionController;
    private AbstractSearchingController searchingController;
    private ArrayList<Location> searchArea;

    public AIController(AbstractSearchingController searchingController, AbstractActionController actionController){
        this.actionController = actionController;
        actionController.setController(this);
        this.searchingController = searchingController;
    }

    @Override
    public void tick() {
        if(ticks % reactionTime == 0) {
            System.out.println("bwoop");
            searchArea = searchingController.getSearchArea();
            Target target = searchingController.search(searchArea);
            System.out.println("search done");
            System.out.println(target);
            System.out.println("beginning pathfind");
            actionController.act(target);
            System.out.println("end pathfind");

        }
        ++ticks;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
    }

    public ArrayList<Location> getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(ArrayList<Location> searchArea) {
        this.searchArea = searchArea;
    }
}
