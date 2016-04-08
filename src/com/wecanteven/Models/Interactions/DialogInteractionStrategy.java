package com.wecanteven.Models.Interactions;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class DialogInteractionStrategy implements InteractionStrategy {

    ArrayList<String> dialog;
    public DialogInteractionStrategy(ArrayList<String> dialog){
        this.dialog = dialog;
    }

    private ArrayList<String> dialog() {
        return dialog;
    }

    @Override
    public void interact(Character c) {

    }
}
