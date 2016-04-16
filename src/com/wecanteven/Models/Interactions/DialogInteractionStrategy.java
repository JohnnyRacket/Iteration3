package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

import java.util.ArrayList;
import java.util.Iterator;

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
    public Iterator<String> getIterator() {
        return dialog.iterator();
    }

    public void endDialogInteraction() {
        //DO SOMETHING
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createToast(4, "YOU GOT A QUEST ITEM!!");
            UIViewFactory.getInstance().createToast(4, "lawl just kidding...");
        },0);
    }

    @Override
    public void interact(Character c) {
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c, getIterator());
        },0);
    }
}
