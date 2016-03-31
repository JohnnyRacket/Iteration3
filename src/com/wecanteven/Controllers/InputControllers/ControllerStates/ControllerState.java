package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 3/31/2016.
 */
public abstract class ControllerState {
    private Map<ActionEnum, Integer> mappings = new HashMap<>();
    private ArrayList<KeyActionBinding> keyBindings = new ArrayList<>();
    private JFrame jFrame;

    public ControllerState(JFrame jFrame){
        this.jFrame = jFrame;
    }

    public abstract void createKeybindings();
    public abstract void destroyKeyBindings();

    public Map<ActionEnum, Integer> getMappings() {
        return mappings;
    }

    public void setMappings(Map<ActionEnum, Integer> mappings) {
        this.mappings = mappings;
    }

    public ArrayList<KeyActionBinding> getKeyBindings() {
        return keyBindings;
    }

    public void setKeyBindings(ArrayList<KeyActionBinding> keyBindings) {
        this.keyBindings = keyBindings;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }
}
