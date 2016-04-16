package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Quests.Questable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class QuestDialogInteractionStrategy implements InteractionStrategy {

    private NPC owner;
    private Questable quest;
    private boolean questStarted;
    private boolean questEnded;

    private ArrayList<String> startDialog;
    private ArrayList<String> endDialog;



    public QuestDialogInteractionStrategy(ArrayList<String> startDialog, ArrayList<String> endDialog, Questable quest){
        this.quest = quest;
        this.startDialog = startDialog;
        this.endDialog = endDialog;
        questStarted = false;
        questEnded = false;
    }


    public Iterator<String> getStartIterator() {
        return startDialog.iterator();
    }

    public Iterator<String> getEndIterator() {
        return endDialog.iterator();
    }


    public void startQuest(Character c) {
        //PLACE ITEM STILL
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c, getStartIterator());
        },0);
        questStarted = true;
    }

    public boolean endQuest(Character c) {
        if(quest.completeQuest(c)) {
            //Show End Quest Dialog
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createDialogView(owner, c, getEndIterator());
            },0);
            questEnded = true;
            return true;
        }
        return false;
    }

    @Override
    public void interact(Character c) {
        if(questStarted){
            if(!questEnded){
                //Quested Started, Lets try to end the quest
                endQuest(c);
            }
        }else {
            //Starting Quest
            startQuest(c);
        }
    }

    @Override
    public void setOwner(NPC npc) {
        this.owner = npc;
    }

}


