package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;

/**
 * Created by John on 3/31/2016.
 */
public class MenuState extends ControllerState {


    @Override
    public void createKeybindings() {
        //example keybinding
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.UP), ()->{
            //add what we want the up key to do here
        }));

    }

    @Override
    public void destroyKeyBindings() {

    }
}
