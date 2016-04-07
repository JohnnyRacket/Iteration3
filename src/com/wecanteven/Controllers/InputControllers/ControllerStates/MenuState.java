package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.AreaView.ViewTime;
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
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.UP), ()->{
            this.setCommandToExecute(()->menus.up());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.DOWN), ()->{
            this.setCommandToExecute(()->menus.down());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SELECT), ()->{
            System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
            this.setCommandToExecute(()->menus.select());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.LEFT), ()->{
            this.setCommandToExecute(()->menus.left());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.RIGHT), ()->{
            this.setCommandToExecute(()->menus.right());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SWAPVIEW), ()->{
            this.setCommandToExecute(()->menus.swap());
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ESCAPE), ()->{
            System.out.println("esc hit");
            this.getController().setPlayState();
            ViewTime.getInstance().register(()->{
                this.getController().clearViews();
            },0);
        }, this.getjFrame(), this.getController()));

    }



    public MenuViewContainer getMenus() {
        return menus;
    }

    public void setMenus(MenuViewContainer menus) {
        this.menus = menus;
    }
}
