package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
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

    public PlayState(JFrame jFrame){
        super(jFrame);
        HashMap<ActionEnum, Integer> mappings = new HashMap<>();
        mappings.put(ActionEnum.NORTH, KeyEvent.VK_W);
        mappings.put(ActionEnum.NORTHEAST, KeyEvent.VK_E);
        mappings.put(ActionEnum.NORTHWEST, KeyEvent.VK_Q);
        mappings.put(ActionEnum.SOUTH, KeyEvent.VK_S);
        mappings.put(ActionEnum.SOUTHEAST, KeyEvent.VK_D);
        mappings.put(ActionEnum.SOUTHWEST, KeyEvent.VK_A);
        this.setMappings(mappings);
    }
    @Override
    public void createKeybindings() {
        System.out.println("creating keybindings");
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTH), ()->{
            //add what we want the up key to do here
            System.out.println("move north hit");
            avatar.move(Direction.NORTH);
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHEAST), ()->{
            //add what we want the up key to do here
            System.out.println("move northeast hit");
            avatar.move(Direction.NORTHEAST);
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHWEST), ()->{
            //add what we want the up key to do here
            System.out.println("move northwest hit");
            avatar.move(Direction.NORTHWEST);
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTH), ()->{
            //add what we want the up key to do here
            System.out.println("move south hit");
            avatar.move(Direction.SOUTH);
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHEAST), ()->{
            //add what we want the up key to do here
            System.out.println("move southeast hit");
            avatar.move(Direction.SOUTHEAST);
        }, this.getjFrame()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHWEST), ()->{
            //add what we want the up key to do here
            System.out.println("move southwest hit");
            avatar.move(Direction.SOUTHWEST);
        }, this.getjFrame()));
    }

    @Override
    public void destroyKeyBindings() {

    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
