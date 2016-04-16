package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.DynamicImages.ConstantDynamicImage;
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
    private boolean repeat;

    private ArrayList<String> startDialog;
    private ArrayList<String> endDialog;



    public QuestDialogInteractionStrategy(ArrayList<String> startDialog, ArrayList<String> endDialog, Questable quest){
        this.quest = quest;
        this.startDialog = startDialog;
        this.endDialog = endDialog;
        questStarted = false;
        repeat = false;
        questEnded = false;
    }


    public Iterator<String> getStartIterator() {
        return startDialog.iterator();
    }

    public Iterator<String> getEndIterator() {
        return endDialog.iterator();
    }


    public void initMap(){
        quest.initQuest(owner.getActionHandler());
    }


    public void startQuest(Character c) {
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c, getStartIterator());
        },0);
        if(!questStarted){
            initMap();
        }
        questStarted = true;
    }

    public boolean endQuest(Character c) {
        repeatQuestDialog();
        if(quest.completeQuest(c)) {
            //Show End Quest Dialog
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createDialogView(owner, c, getEndIterator());
            },0);
            questEnded = true;
            return true;
        }
        startQuest(c);
        return false;
    }

    @Override
    public void interact(Character c) {
        if(questStarted){
            System.out.println("Quest Started but not Ended");
            if(!questEnded){
                //Quested Started, Lets try to end the quest
                endQuest(c);
            }
        }else {
            System.out.println("Quest Started");
            //Starting Quest
            startQuest(c);
        }
    }

    @Override
    public void setOwner(NPC npc) {
        this.owner = npc;
    }

    public void repeatQuestDialog() {
        if(!repeat) {
            startDialog.add(0, "Quest already started, but here's a refresher");
            repeat = true;
        }
    }

}


