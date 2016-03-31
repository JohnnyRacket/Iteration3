package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Controllers.InputControllers.ControllerStates.ControllerState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.DialogState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.MenuState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.PlayState;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;

import javax.swing.*;

/**
 * Created by John on 3/31/2016.
 */
public class MainController {

    private ControllerState state;

    private MenuState menuState;
    private PlayState playState;
    private DialogState dialogState;

    public MainController(JFrame window){
        menuState = new MenuState(window);
        playState = new PlayState(window);
        dialogState = new DialogState(window);
    }

    private void removeState(){
        if (state != null) {
            state.destroyKeyBindings();
        }
    }

    public void setMenuState(MenuViewContainer menuViewContainer){
        removeState();
        menuState.setMenus(menuViewContainer);
        menuState.createKeybindings();
        this.state = menuState;

    }
}
