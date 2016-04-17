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
        mappings.put(ActionEnum.ABILITYINVENTORY, KeyEvent.VK_O);
        mappings.put(ActionEnum.STATS, KeyEvent.VK_K);
        mappings.put(ActionEnum.TRADE, KeyEvent.VK_T);
        mappings.put(ActionEnum.ATTACK, KeyEvent.VK_L);
        mappings.put(ActionEnum.INTERACT, KeyEvent.VK_ENTER);
        mappings.put(ActionEnum.ESCAPE, KeyEvent.VK_ESCAPE);
        mappings.put(ActionEnum.UP, KeyEvent.VK_J);
        mappings.put(ActionEnum.DOWN, KeyEvent.VK_H);
        this.setMappings(mappings);
    }
    @Override
    public void createKeybindings() {
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTH), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTH));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHEAST), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTHEAST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.NORTHWEST), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.NORTHWEST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTH), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTH));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHEAST), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTHEAST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.SOUTHWEST), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.SOUTHWEST));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ITEMINVENTORY), ()->{
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createInventoryView(avatar.getCharacter());
            },0);
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ABILITYINVENTORY), ()->{
            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createAbilityView(avatar.getCharacter());
            },0);
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.STATS), ()->{

            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createStatsView(avatar.getCharacter());
            },0);
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.INTERACT), ()->{
            avatar.interactWithTile();
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ATTACK), ()->{

            avatar.attack(avatar.getCharacter().getDirection());
            //this.setContinuousCommandToExecute(()->avatar.useAbility(5));
        }, this.getjFrame(), this.getController()));
        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.ESCAPE), ()->{

            ViewTime.getInstance().register(()->{
                UIViewFactory.getInstance().createPauseMenu();
            },0);
        }, this.getjFrame(), this.getController()));

        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.UP), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.UP));
        }, this.getjFrame(), this.getController()));

        this.getKeyBindings().add( new KeyActionBinding(this.getMappings().get(ActionEnum.DOWN), ()->{
            this.setContinuousCommandToExecute(()->avatar.move(Direction.DOWN));
        }, this.getjFrame(), this.getController()));
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
