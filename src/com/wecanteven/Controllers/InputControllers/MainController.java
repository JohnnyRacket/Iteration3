package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Controllers.InputControllers.ControllerStates.ControllerState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.DialogState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.MenuState;
import com.wecanteven.Controllers.InputControllers.ControllerStates.PlayState;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.ModelTime.Tickable;
import com.wecanteven.ViewEngine;

import javax.swing.*;

/**
 * Created by John on 3/31/2016.
 */
public class MainController implements Tickable{

    private ControllerState state;
    private Avatar avatar;
    private ViewEngine viewEngine;

    private MenuState menuState;
    private PlayState playState;
    private DialogState dialogState;

    public MainController(ViewEngine window){
        this.viewEngine = window;
        menuState = new MenuState(window, this);
        playState = new PlayState(window, this);
        dialogState = new DialogState(window, this);
    }
//testing avatar movement
    public MainController(ViewEngine window,Avatar avatar){
        this.avatar = avatar;
        this.viewEngine = window;
        menuState = new MenuState(window, this);
        playState = new PlayState(window, this);
        dialogState = new DialogState(window, this);
    }

    private void removeState(){
        if (state != null) {
            state.destroyKeyBindings();
            menuState.setMenus(null);
            System.out.println("bloopey");
        }
    }

    public void setMenuState(MenuViewContainer menuViewContainer){
        removeState();
        menuState.setCommandToExecute(null);
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

    public void changeView(SwappableView view){
        viewEngine.getManager().addView(view);
    }

    public void clearViews(){
        viewEngine.getManager().clear();
    }

    public void popView(){
        viewEngine.getManager().popView();
    }

    @Override
    public void tick() {
        if(state.getCommandToExecute() != null) {
            //System.out.println("executing controller command");
            state.getCommandToExecute().execute();
            state.setCommandToExecute(null);

        }
        if(state.getContinuousCommandToExecute() != null) {
            //System.out.println("executing controller command");
            state.getContinuousCommandToExecute().execute();
            //state.setContinuousCommandToExecute(null);

        }
    }

    public void removeKeyActive(){
        state.setContinuousCommandToExecute(null);
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
