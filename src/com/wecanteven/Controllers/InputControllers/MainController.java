package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Controllers.InputControllers.ControllerStates.ControllerState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.DialogState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.MenuState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.PlayState;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.ModelTime.Tickable;

import javax.swing.*;

/**
 * Created by John on 3/31/2016.
 */
public class MainController implements Tickable{

    private ControllerState state;
    private Avatar avatar;

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

    public void setPlayState(){
        removeState();
        playState.setAvatar(avatar);
        playState.createKeybindings();
        this.state = playState;
    }

    public void setDialogState(){//needs to take in a Dialog obkect once its made
        removeState();
        //maybe add more here
        dialogState.createKeybindings();
        this.state = dialogState;
    }

    @Override
    public void tick() {

        if(state.getCommandToExecute() != null) {
            System.out.println("executing controller command");
            state.getCommandToExecute().execute();
            state.setCommandToExecute(null);
        }
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
