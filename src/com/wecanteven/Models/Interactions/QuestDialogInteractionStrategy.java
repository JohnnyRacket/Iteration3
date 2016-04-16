package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Quests.Questable;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class QuestDialogInteractionStrategy extends DialogInteractionStrategy {

    private Questable quest;


    public QuestDialogInteractionStrategy(ArrayList<String> dialog, Questable quest){
        super(dialog);
        this.quest = quest;

    }
}
