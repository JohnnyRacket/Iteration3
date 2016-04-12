package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

import java.util.ArrayList;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class DialogInteractionStrategy implements InteractionStrategy {

    private NPC owner;
    private int currentDialog;
    ArrayList<String> dialog;

    public DialogInteractionStrategy(ArrayList<String> dialog){
        this.dialog = dialog;
        currentDialog = 0;
    }
    public void setOwner(NPC npc) {
        this.owner = npc;
    }
    public String getNextDialog() {
        return dialog.get(currentDialog++ % dialog.size());
    }

    public ArrayList<String> getDialog() {
        return dialog;
    }


    @Override
    public void interact(Character c) {
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c, getNextDialog());
        },0);
    }
}
