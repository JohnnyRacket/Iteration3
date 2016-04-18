package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBindSetter;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;

import javax.swing.*;
import java.util.Map;

/**
 * Created by John on 4/11/2016.
 */
public class KeyBindState extends ControllerState {
    Map<ActionEnum, Integer> map;
    KeyActionBindSetter keySetter;
    ActionEnum aEnum;
    MenuViewContainer container;
    public KeyBindState(JFrame jFrame, MainController controller) {
        super(jFrame, controller);

    }

    @Override
    public void createKeybindings() {


        this.getKeyBindings().add( new KeyActionBindSetter(0,()->{
            getController().popView();
            getController().setMenuState(container);
        },getjFrame(),getController(), aEnum, map));

    }
    public ActionEnum getaEnum() {
        return aEnum;
    }

    public void setaEnum(ActionEnum aEnum) {
        this.aEnum = aEnum;
    }

    public Map<ActionEnum, Integer> getMap() {
        return map;
    }

    public void setMap(Map<ActionEnum, Integer> map) {
        this.map = map;
    }

    public MenuViewContainer getContainer() {
        return container;
    }

    public void setContainer(MenuViewContainer container) {
        this.container = container;
    }
}
