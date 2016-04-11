package com.wecanteven.Controllers.InputControllers.ControllerStates;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.KeyActionBinding;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.GameLaunchers.GameLauncher;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
import com.wecanteven.UtilityClasses.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
        mappings.put(ActionEnum.STATS, KeyEvent.VK_K);
        mappings.put(ActionEnum.TRADE, KeyEvent.VK_T);
        mappings.put(ActionEnum.ATTACK, KeyEvent.VK_L);
        mappings.put(ActionEnum.ESCAPE, KeyEvent.VK_ESCAPE);
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
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.STATS), ()->{
            System.out.println("open stats hit");
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createStatsView(avatar.getCharacter());
            },0);
        }, this.getjFrame(), this.getController()));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.TRADE), ()->{
            /* TODO: REMOVE ALL OF THIS SOON



             */
            System.out.println("open trade menu");
            //TODO: THIS IS NOT PROGRAMING!
            NPC c = new NPC(getAvatar().getCharacter().getActionHandler(), Direction.NORTH, new TradeInteractionStrategy());
            c.pickup(new ChestEquipableItem("Buyable Chestplate", 5, null));
            c.interact(getAvatar().getCharacter());
        }, this.getjFrame(), this.getController()));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////



        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SAVE), ()->{
            System.out.println("Trying to Save");
            new SaveToXMLFile("save1.xml").saveGame();
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ATTACK), ()->{
            System.out.println("use ability");
            avatar.attack();
            //this.setContinuousCommandToExecute(()->avatar.useAbility(5));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ESCAPE), ()->{
            System.out.println("open pause menu hit");
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createPauseMenu();
            },0);
        }, this.getjFrame(), this.getController()));
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
