package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.UtilityClasses.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by John on 3/31/2016.
 */
public class PlayState extends ControllerState {
    private Avatar avatar;

    public PlayState(JFrame jFrame, MainController controller){
        super(jFrame, controller);
        HashMap<ActionEnum, Integer> mappings = new HashMap<>();
        mappings.put(ActionEnum.NORTH, KeyEvent.VK_W);
        mappings.put(ActionEnum.NORTHEAST, KeyEvent.VK_E);
        mappings.put(ActionEnum.NORTHWEST, KeyEvent.VK_Q);
        mappings.put(ActionEnum.SOUTH, KeyEvent.VK_S);
        mappings.put(ActionEnum.SOUTHEAST, KeyEvent.VK_D);
        mappings.put(ActionEnum.SOUTHWEST, KeyEvent.VK_A);
        mappings.put(ActionEnum.ITEMINVENTORY, KeyEvent.VK_I);
        this.setMappings(mappings);
    }
    @Override
    public void createKeybindings() {
        System.out.println("creating keybindings");
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTH), ()->{
            System.out.println("move north hit");
            this.setCommandToExecute(()->avatar.move(Direction.NORTH));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHEAST), ()->{
            System.out.println("move northeast hit");
            this.setCommandToExecute(()->avatar.move(Direction.NORTHEAST));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHWEST), ()->{
            System.out.println("move northwest hit");
            this.setCommandToExecute(()->avatar.move(Direction.NORTHWEST));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTH), ()->{
            System.out.println("move south hit");
            this.setCommandToExecute(()->avatar.move(Direction.SOUTH));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHEAST), ()->{
            System.out.println("move southeast hit");
            this.setCommandToExecute(()->avatar.move(Direction.SOUTHEAST));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHWEST), ()->{
            System.out.println("move southwest hit");
            this.setCommandToExecute(()->avatar.move(Direction.SOUTHWEST));
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ITEMINVENTORY), ()->{
            System.out.println("open inventory hit");
            SwappableView view = UIViewFactory.getInstance().createInventoryView();
            controller.setMenuState(view.getMenuViewContainer());
            controller.changeView(view);

        }, this.getjFrame()));
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
