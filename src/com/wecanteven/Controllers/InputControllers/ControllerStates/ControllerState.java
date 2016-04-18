package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.KeyInteractionCommand;
import com.wecanteven.Controllers.InputControllers.MainController;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private MainController controller;

    private KeyInteractionCommand commandToExecute;
    private KeyInteractionCommand continuousCommandToExecute;

    public ControllerState(JFrame jFrame, MainController controller){
        this.controller = controller;
        this.jFrame = jFrame;
    }

    public abstract void createKeybindings();
    public void destroyKeyBindings(){
        for(KeyListener binding: this.getKeyBindings()){
            this.getjFrame().removeKeyListener(binding);
        }
    }

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

    public KeyInteractionCommand getCommandToExecute() {
        return commandToExecute;
    }

    public void setCommandToExecute(KeyInteractionCommand commandToExecute) {
        this.commandToExecute = commandToExecute;
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public KeyInteractionCommand getContinuousCommandToExecute() {
        return continuousCommandToExecute;
    }

    public void setContinuousCommandToExecute(KeyInteractionCommand continuousCommandToExecute) {
        this.continuousCommandToExecute = continuousCommandToExecute;
    }
}
