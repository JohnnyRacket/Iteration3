package com.wecanteven.Controllers.AIControllers;

import com.wecanteven.Controllers.AIControllers.ActionControllers.AbstractActionController;
import com.wecanteven.Controllers.AIControllers.SearchingControllers.AbstractSearchingController;
import com.wecanteven.Controllers.AIControllers.Targets.Target;
import com.wecanteven.Models.ModelTime.Tickable;

/**
 * Created by John on 4/5/2016.
 */
public class AIController implements Tickable {

    private int reactionTime = 15;
    private int ticks;
    private AbstractActionController actionController;
    private AbstractSearchingController searchingController;

    public AIController(AbstractSearchingController searchingController, AbstractActionController actionController){
        this.actionController = actionController;
        this.searchingController = searchingController;
    }

    @Override
    public void tick() {
        if(ticks % reactionTime == 0) {
            Target target = searchingController.search();
            actionController.act(target);
        }
        ++ticks;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
    }
}
