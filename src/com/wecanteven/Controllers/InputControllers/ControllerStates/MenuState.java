package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by John on 3/31/2016.
 */
public class MenuState extends ControllerState {

    private MenuViewContainer menus;

    public MenuState(JFrame jFrame, MainController controller){
        super(jFrame, controller);
        HashMap<ActionEnum, Integer> mappings = new HashMap<>();
        mappings.put(ActionEnum.UP,KeyEvent.VK_UP);
        mappings.put(ActionEnum.DOWN, KeyEvent.VK_DOWN);
        mappings.put(ActionEnum.LEFT, KeyEvent.VK_LEFT);
        mappings.put(ActionEnum.RIGHT, KeyEvent.VK_RIGHT);
        mappings.put(ActionEnum.SELECT, KeyEvent.VK_ENTER);
        mappings.put(ActionEnum.SWAPVIEW, KeyEvent.VK_SPACE);
        mappings.put(ActionEnum.ESCAPE, KeyEvent.VK_ESCAPE);
        this.setMappings(mappings);
    }

    @Override
    public void createKeybindings() {
        //example keybinding
        System.out.println("creating keybindings");
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.UP), ()->{
            System.out.println("up hit");
            this.setCommandToExecute(()->menus.up());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.DOWN), ()->{
            System.out.println("down hit");
            this.setCommandToExecute(()->menus.down());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SELECT), ()->{
            System.out.println("select hit");
            this.setCommandToExecute(()->menus.select());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.LEFT), ()->{
            System.out.println("left hit");
            this.setCommandToExecute(()->menus.left());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.RIGHT), ()->{
            System.out.println("right hit");
            this.setCommandToExecute(()->menus.right());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SWAPVIEW), ()->{
            System.out.println("swap hit");
            this.setCommandToExecute(()->menus.swap());
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ESCAPE), ()->{
            System.out.println("esc hit");
            controller.setPlayState();
            controller.clearViews();
        }, this.getjFrame()));

    }


    public MenuViewContainer getMenus() {
        return menus;
    }

    public void setMenus(MenuViewContainer menus) {
        this.menus = menus;
    }
}
