package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBindSetter;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;

import javax.swing.*;

/**
 * Created by John on 4/11/2016.
 */
public class KeyBindState extends ControllerState {
    ControllerState state;
    KeyActionBindSetter keySetter;
    ActionEnum aEnum;
    public KeyBindState(JFrame jFrame, MainController controller, ControllerState state, ActionEnum aEnum) {
        super(jFrame, controller);
        this.state = state;
        this.aEnum = aEnum;
    }

    @Override
    public void createKeybindings() {
        this.keySetter = new KeyActionBindSetter(0,null,getjFrame(),getController(), aEnum, this);

    }
}
