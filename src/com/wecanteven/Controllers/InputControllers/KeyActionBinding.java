package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by John on 3/31/2016.
 */
public class KeyActionBinding implements KeyListener{

    private Integer key;
    private KeyInteractionCommand action;
    private MainController controller;

    public KeyActionBinding(Integer key, KeyInteractionCommand action, JFrame jFrame, MainController controller){
        this.key = key;
        this.action = action;
        this.controller = controller;
        jFrame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == key){
            action.execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.removeKeyActive();
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public KeyInteractionCommand getAction() {
        return action;
    }

    public void setAction(KeyInteractionCommand action) {
        this.action = action;
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
