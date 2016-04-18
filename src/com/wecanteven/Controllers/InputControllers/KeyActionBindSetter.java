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
    ActionEnum aEnum;
    Map<ActionEnum, Integer> map;

    public KeyActionBindSetter(Integer key, KeyInteractionCommand action, JFrame jFrame, MainController controller,ActionEnum aEnum, Map<ActionEnum, Integer> map) {
        super(key, action, jFrame, controller);
        this.map = map;
        this.aEnum = aEnum;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("blurble");
        System.out.println(map);
        map.put(this.aEnum,e.getKeyCode());
        System.out.println(map);
        getAction().execute();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
