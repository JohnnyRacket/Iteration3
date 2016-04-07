package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.SaveLoad.Load.LoadFromXMLFile;
import com.wecanteven.SaveLoad.Load.LoadGame;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
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
        mappings.put(ActionEnum.SAVE, KeyEvent.VK_P);
        mappings.put(ActionEnum.ITEMINVENTORY, KeyEvent.VK_I);
        this.setMappings(mappings);
    }
    @Override
    public void createKeybindings() {
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTH), ()->{
            System.out.println("move north hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTH));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHEAST), ()->{
            System.out.println("move northeast hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTHEAST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHWEST), ()->{
            System.out.println("move northwest hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTHWEST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTH), ()->{
            System.out.println("move south hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTH));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHEAST), ()->{
            System.out.println("move southeast hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTHEAST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHWEST), ()->{
            System.out.println("move southwest hit");
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTHWEST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ITEMINVENTORY), ()->{
            System.out.println("open inventory hit");
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createInventoryView(avatar.getCharacter());
            },0);
            //this.getController().setMenuState(view.getMenuViewContainer());
            //this.getController().changeView(view);
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SAVE), ()->{
            System.out.println("Trying to Save");
            new SaveToXMLFile("save1.xml").saveGame();
        }, this.getjFrame(), this.getController()));
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
