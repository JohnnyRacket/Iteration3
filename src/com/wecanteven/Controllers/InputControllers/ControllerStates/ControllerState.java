package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 3/31/2016.
 */
public abstract class ControllerState {
    private Map<ActionEnum, KeyEvent> mappings = new HashMap<>();

    public abstract void createKeybindings();
    public abstract void destroyKeyBindings();

    public Map<ActionEnum, KeyEvent> getMappings() {
        return mappings;
    }

    public void setMappings(Map<ActionEnum, KeyEvent> mappings) {
        this.mappings = mappings;
    }
}
