package com.wecanteven.Controllers.InputControllers;

import com.wecanteven.Controllers.InputControllers.ControllerStates.*;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.ModelTime.Tickable;
import com.wecanteven.UtilityClasses.Sound;
import com.wecanteven.ViewEngine;

import java.util.Map;

/**
 * Created by John on 3/31/2016.
 */
public class MainController implements Tickable{

    private ControllerState state;
    private Avatar avatar;
    private ViewEngine viewEngine;
    private MainMenuState mainMenuState;
    private MenuState menuState;
    private PlayState playState;
    private KeyBindState keyBindState;
    private DialogState dialogState;

    public MainController(ViewEngine window){
        this.viewEngine = window;
        mainMenuState = new MainMenuState(window, this);
        menuState = new MenuState(window, this);
        playState = new PlayState(window, this);
        dialogState = new DialogState(window, this);
        keyBindState = new KeyBindState(window,this);
    }
//testing avatar movement
    public MainController(ViewEngine window,Avatar avatar){
        this.avatar = avatar;
        this.viewEngine = window;
        mainMenuState = new MainMenuState(window, this);
        menuState = new MenuState(window, this);
        playState = new PlayState(window, this);
        dialogState = new DialogState(window, this);
    }

    private void removeState(){
        if (state != null) {
            state.destroyKeyBindings();
            menuState.setMenus(null);
        }
    }

    public void setMainMenuState(MenuViewContainer menuViewContainer){
        removeState();
        mainMenuState.setCommandToExecute(null);
        mainMenuState.setMenus(menuViewContainer);
        mainMenuState.createKeybindings();
        this.state = mainMenuState;

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

    public void setKeyBindState(Map<ActionEnum, Integer> map, ActionEnum actionEnum){
        keyBindState.setMap(map);
        keyBindState.setContainer(((MenuState) state).getMenus());
        removeState();
        keyBindState.setaEnum(actionEnum);
        keyBindState.createKeybindings();
        this.state = keyBindState;
    }

    public void setDialogState(ActionEnum actionEnum){//needs to take in a Dialog obkect once its made

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
            //
            state.getCommandToExecute().execute();
            state.setCommandToExecute(null);

        }
        if(state.getContinuousCommandToExecute() != null) {
            //
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

    public MenuState getMenuState() {
        return menuState;
    }

    public PlayState getPlayState() {
        return playState;
    }

    public DialogState getDialogState() {
        return dialogState;
    }
}
