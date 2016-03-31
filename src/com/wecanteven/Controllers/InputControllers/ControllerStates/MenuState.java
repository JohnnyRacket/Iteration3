package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by John on 3/31/2016.
 */
public class MenuState extends ControllerState {

    private MenuViewContainer menus;

    public MenuState(){
        HashMap<ActionEnum, Integer> mappings = new HashMap<>();
        mappings.put(ActionEnum.UP,KeyEvent.VK_UP);
    }

    @Override
    public void createKeybindings() {
        //example keybinding
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.UP), ()->{
            //add what we want the up key to do here
            menus.up();
            System.out.println("up hit");
        }));

    }

    @Override
    public void destroyKeyBindings() {

    }

    public MenuViewContainer getMenus() {
        return menus;
    }

    public void setMenus(MenuViewContainer menus) {
        this.menus = menus;
    }
}
