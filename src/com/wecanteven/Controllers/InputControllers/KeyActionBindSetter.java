package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Controllers.InputControllers.ControllerStates.ControllerState;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Created by John on 4/17/2016.
 */
public class KeyActionBindSetter extends KeyActionBinding {
    ControllerState controllerState;
    ActionEnum aEnum;

    public KeyActionBindSetter(Integer key, KeyInteractionCommand action, JFrame jFrame, MainController controller,ActionEnum aEnum, ControllerState controllerState) {
        super(key, action, jFrame, controller);
        this.controllerState = controllerState;
        this.aEnum =aEnum;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
            Map<ActionEnum, Integer> map = controllerState.getMappings();
            map.put(this.aEnum,e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
