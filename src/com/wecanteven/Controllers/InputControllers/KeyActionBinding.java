package com.wecanteven.Controllers.InputControllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by John on 3/31/2016.
 */
public class KeyActionBinding implements KeyListener{

    private KeyEvent key;
    private KeyInteractionCommand action;

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e == key){
            action.execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
