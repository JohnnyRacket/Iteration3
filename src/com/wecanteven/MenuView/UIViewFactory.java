package com.wecanteven.MenuView;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Controllers.InputControllers.ActionEnum;
import com.wecanteven.Controllers.InputControllers.ControllerStates.ControllerState;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.GameLaunching.GameLaunchers.NewGameLauncher;
import com.wecanteven.MenuView.DrawableContainers.Decorators.*;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.ColumnatedCompositeContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.CustomScaleColumnsContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.RowedCompositeContainer;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;
import com.wecanteven.MenuView.DrawableLeafs.BackgroundImageDrawable;
import com.wecanteven.MenuView.DrawableLeafs.HUDview.StatsHUD;
import com.wecanteven.MenuView.DrawableLeafs.KeyBindView;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGrid;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGridWithCenterTitle;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.*;
import com.wecanteven.MenuView.DrawableLeafs.Toaster.Toast;
import com.wecanteven.MenuView.UIObjectCreationVisitors.BuyableUIObjectCreationVisitor;
import com.wecanteven.MenuView.UIObjectCreationVisitors.EquippableUIObjectCreationVisitor;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.SaveLoad.Load.LoadFromXMLFile;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.ViewEngine;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class UIViewFactory {
    private static final String PATH = "resources/Saves/";


    private JFrame jframe;
    private MainController controller;
    private ViewEngine vEngine;
    private ModelEngine mEngine;
    private Avatar avatar;

    private static UIViewFactory ourInstance = new UIViewFactory();

    private UIViewFactory(){}

    public static UIViewFactory getInstance(){return ourInstance;}

    public void setJFrame(JFrame jFrame){
        this.jframe = jFrame;
    }


    public void createHUDView(Character character){
        StatsHUD statsHUD = new StatsHUD(character.getStats());
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(statsHUD);
        statsHUD.setHeight(150);
        statsHUD.setWidth(300);
        statsHUD.setBgColor(new Color(.5f,.5f,.5f,.5f));
        SwappableView view = new SwappableView();
        view.addDrawable(horiz);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addPermView(view);
        },0);
    }
    public void createAbilityView(){

    }
    public void createMainMenuView(){
        //make menu
        ScrollableMenu menu = new ScrollableMenu(400, 400);
        menu.setSelectedColor(Color.cyan);
        menu.setBgColor(Config.TRANSMEDIUMGREY);
        //make menu list
        NavigatableList list = new NavigatableList();
        list.addItem(
                new ScrollableMenuItem("New Game", () -> {
                    NewGameLauncher template = new NewGameLauncher(controller, mEngine, vEngine);
                    template.launch();
                    resumeGame();
            })
        );
        list.addItem(createLoadMenu(menu, list));
        list.addItem(new ScrollableMenuItem("Exit", () -> {System.exit(0);}));
        menu.setList(list);
        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu
        TitleBarDecorator title = new TitleBarDecorator(menu,"Can Periwinkle Even", Config.CINNIBAR);
        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(title);
        VerticalCenterContainer vertCenter = new VerticalCenterContainer(horizCenter);
        view.addDrawable(vertCenter);
        view.addNavigatable(menu);

        controller.setMainMenuState(view.getMenuViewContainer());

        ViewTime.getInstance().register(()->{
            this.getController().clearViews();
            this.getvEngine().clear();
            vEngine.getManager().addView(view);
        },0);

    }

    public void createStatsView(Character character){


        NavigatableList skillList = new NavigatableList();
        skillList.addItem(new ScrollableMenuItem("placeholder skill", ()->{

        }));

        ScrollableMenu skillMenu = new ScrollableMenu(300,650);
        skillMenu.setList(skillList);

        Stats stats = character.getStats();
        NavigatableList statsList = new NavigatableList();

        statsList.addItem(new SplitTextScrollableItem("Level: " , ""+ stats.getLevel(),null));
        statsList.addItem(new SplitTextScrollableItem("Health: ", stats.getHealth() + "/" + stats.getMaxHealth(),null));
        statsList.addItem(new SplitTextScrollableItem("Mana: ", "" + stats.getMana() + "/" + stats.getMaxMana(),null));
        statsList.addItem(new SplitTextScrollableItem("Experience: ", "" + stats.getExperience() + "/" + "?????",null));
        statsList.addItem(new SplitTextScrollableItem("Lives: ", "" + stats.getLives(),null));

        statsList.addItem(new ScrollableMenuItem("",null));
        statsList.addItem(new SplitTextScrollableItem("Agility: ", "" + stats.getAgility(),null));
        statsList.addItem(new SplitTextScrollableItem("Strength: ", "" + stats.getStrength(),null));
        statsList.addItem(new SplitTextScrollableItem("Hardiness: ", "" + stats.getHardiness(),null));
        statsList.addItem(new SplitTextScrollableItem("Movement: ", "" + stats.getMovement(),null));
        statsList.addItem(new SplitTextScrollableItem("Intellect: ", "" + stats.getIntellect(),null));

        statsList.addItem(new ScrollableMenuItem("" ,null));//spacer
        statsList.addItem(new SplitTextScrollableItem("Offensive Rating: ", "" + stats.getOffensiveRating(),null));
        statsList.addItem(new SplitTextScrollableItem("Defensive Rating: ", "" + stats.getDefensiveRating(),null));
        statsList.addItem(new SplitTextScrollableItem("Armor Rating: ", "" + stats.getArmorRating(),null));

        ScrollableMenu menu = new ScrollableMenu(300,600);
        menu.setList(statsList);

        ColumnatedCompositeContainer columns = new ColumnatedCompositeContainer();
        columns.setWidth(600);
        columns.setHeight(550);
        columns.addDrawable(menu);
        columns.addDrawable(skillMenu);

        TitleBarDecorator title = new TitleBarDecorator(columns, "Skills/Stats", Config.TEAL);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(title);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);

        SwappableView view = new SwappableView();
        view.addNavigatable(skillMenu);
        view.addDrawable(anim);

        ViewTime.getInstance().register(()->{
            createGreyBackground();
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());


    }

    public void createInventoryView(Character character){

        NavigatableGrid menu = new NavigatableGrid(400, 400, 5, 5);
        NavigatableGrid equipMenu = new NavigatableGrid(100, 400, 1, 4);

        EquippableUIObjectCreationVisitor visitor = new EquippableUIObjectCreationVisitor(this, menu, equipMenu);
        character.accept(visitor);
        NavigatableList list = visitor.getInventoryItems();
        NavigatableList equiplist = visitor.getEquippedItems();
        //make menu
        menu.setBgColor(Config.PAPYRUS);

        equipMenu.setBgColor(Config.DARKPAPYRUS);

        //NavigatableList equiplist = new NavigatableList();
        //equiplist.addItem();

        menu.setList(list);
        equipMenu.setList(equiplist);
        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu
        CustomScaleColumnsContainer columns  = new CustomScaleColumnsContainer(new int[]{4,1});
        columns.setHeight(400);
        columns.setWidth(600);

        columns.addDrawable(menu);
        columns.addDrawable(equipMenu);

        TitleBarDecorator title = new TitleBarDecorator(columns, "Inventory/Equipment", Config.TEAL);
        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(title);
        VerticalCenterContainer vertCenter = new VerticalCenterContainer(horizCenter);
        AnimatedCollapseDecorator animation = new AnimatedCollapseDecorator(vertCenter);
//        view.addDrawable(vertCenter);


        view.addDrawable(animation);
        view.addNavigatable(menu);
        view.addNavigatable(equipMenu);
        //return created swappable view
        ViewTime.getInstance().register(()->{
            createGreyBackground();
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());
    }

    public void createEquippableItemMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, EquipableItem item){
        EquippableUIObjectCreationVisitor visitor = new EquippableUIObjectCreationVisitor(this,invHolder,eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Equip", () ->{

            character.equipItem(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                //createInventoryView(avatar.getCharacter());
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventoryItems());
                eqHolder.setList(visitor.getEquippedItems());
            },0);

            controller.setMenuState(container);
        }));
        list.addItem(new ScrollableMenuItem("Drop", () ->{

            character.drop(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventoryItems());
                eqHolder.setList(visitor.getEquippedItems());
            },0);
            controller.setMenuState(container);
        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{

            ViewTime.getInstance().register(() ->{
                controller.popView();

            },0);
            controller.setMenuState(container);
        }));
        ScrollableMenu menu = new ScrollableMenu(100,100);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);
        menu.setBgColor(Config.CINNIBAR);
        menu.setList(list);
        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(anim);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());

    }
    public void createEquippedItemMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, EquipableItem item){
        EquippableUIObjectCreationVisitor visitor = new EquippableUIObjectCreationVisitor(this, invHolder, eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Unequip", () ->{

            character.unequipItem(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventoryItems());
                eqHolder.setList(visitor.getEquippedItems());
            },0);
            controller.setMenuState(container);
        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{

            ViewTime.getInstance().register(() ->{
                controller.popView();
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventoryItems());
                eqHolder.setList(visitor.getEquippedItems());
            },0);
            controller.setMenuState(container);
        }));
        ScrollableMenu menu = new ScrollableMenu(100,70);
        menu.setBgColor(Config.CINNIBAR);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);

        menu.setList(list);
        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(anim);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());
    }
    public SwappableView createUsableItemMenu(){
        return null;
    }
    public SwappableView createConsumableItemMenu(){
        return null;
    }
    public SwappableView createAbilityItemMenu(){
        return null;
    }
    //WHEN THE SHOPPERKEEPER TRIES TO SELL TO THE SHOPPER
    public void createBuyableItemMenu(BuyableUIObjectCreationVisitor visitor, NPC shopOwner, Character buyer, TakeableItem item){
        NavigatableList list = new NavigatableList();
        TradeInteractionStrategy interactionStrategy = (TradeInteractionStrategy) shopOwner.getInteraction();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Buy: " + item.getValue() + " Gold", () ->{

            if(!buyer.getItemStorage().inventoryIsFull() && interactionStrategy.sell(item)){
                shopOwner.getItemStorage().removeItem(item);
                buyer.pickup(item);
                createToast(3, "You've purchased a " + item.getName() + " for " + item.getValue() + " gold!");
            }else {
                //PLAYER CANT BUY IF HIS INVENTORY IS FULL
                if(buyer.getItemStorage().inventoryIsFull()){
                    createToast(3, "Your inventory is full!");

                }else {
                    //PLAYER CANT BUY IF HE DOESNT HAVE MONEY
                    createToast(5, "You can't afford a " + item.getName() + " for " + item.getValue() + " gold!");

                }
            }


            ViewTime.getInstance().register(() ->{

                controller.popView();
                visitor.visitBoth();
                visitor.updateList();
            },0);
            controller.setMenuState(container);

        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{

            ViewTime.getInstance().register(() ->{
                controller.popView();
            },0);
            controller.setMenuState(container);
        }));
        ScrollableMenu menu = new ScrollableMenu(100,100);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);

        menu.setList(list);
        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(vert);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());

    }
    //WHEN THE SHOPPER TRIES TO SELL TO THE SHOPKEEPER
    public void createSellableItemMenu(NPC shopOwner, Character buyer, TakeableItem item){
        NavigatableList list = new NavigatableList();
        TradeInteractionStrategy interactionStrategy = (TradeInteractionStrategy) shopOwner.getInteraction();
        list.addItem(new ScrollableMenuItem("Sell: " + item.getValue() + " Gold", () ->{

            if(!shopOwner.getItemStorage().inventoryIsFull() && interactionStrategy.buy(item)){
                buyer.getItemStorage().removeItem(item);
                shopOwner.pickup(item);
                createToast(3, "You've sold a " + item.getName() + " for " + item.getValue() + " gold!");
            }else {
                //SHOPOWNER CANT BUY IF HIS INVENTORY IS FULL
                if(shopOwner.getItemStorage().inventoryIsFull()){
                    createToast(3, "Shop Owner's inventory is full!");

                }else {
                    //SHOPOWNER CANT BUY IF HE DOESNT HAVE MONEY
                    createToast(5, "The Shopkeeper can't afford a " + item.getName() + " for " + item.getValue() + " gold!");
                }
            }


            ViewTime.getInstance().register(() ->{
                controller.popView();
                createTradeView(shopOwner, buyer, false);
            },0);

        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{

            ViewTime.getInstance().register(() ->{
                controller.popView();
                createTradeView(shopOwner, buyer, false);
            },0);
        }));
        ScrollableMenu menu = new ScrollableMenu(100,100);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);

        menu.setList(list);
        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(vert);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());

    }
    public void createPauseMenu(){
        pauseGame();
        ScrollableMenu menu = new ScrollableMenu(300,300);
        NavigatableList list = new NavigatableList();
        list.addItem(createSaveMenu(menu, list));
        list.addItem(createLoadMenu(menu, list));
        list.addItem(new ScrollableMenuItem("Key Bindings", ()->{
            //go to key binding menu
            NavigatableList keyBindList = new NavigatableList();
            keyBindList.addItem(new ScrollableMenuItem("Menu KeyBindings", ()->{
                createKeyBindMenu(controller.getMenuState());
            }));
            keyBindList.addItem(new ScrollableMenuItem("Game KeyBindings", ()->{
                createKeyBindMenu(controller.getPlayState());
            }));
            keyBindList.addItem(new ScrollableMenuItem("Dialogue KeyBindings", ()->{
                createKeyBindMenu(controller.getDialogState());
            }));
            keyBindList.addItem(new ScrollableMenuItem("Back", ()->{
                menu.setList(list);
            }));
            menu.setList(keyBindList);

        }));
        list.addItem(new ScrollableMenuItem("Exit to Main Menu", ()->{
            //exit to main menu
            //what all do i need to do here?
            //dump things registered in the time models? (add clear functions to time models)
            //Ask if user wants to save? <- Josh wants this ;)
            //switch view to main menu view
            ViewTime.getInstance().register(()->{
                createMainMenuView();
            },0);
        }));

        menu.setList(list);
        TitleBarDecorator title = new TitleBarDecorator(menu,"Pause Menu", Config.CINNIBAR);
       // RectangleShadowDecorator shadow = new RectangleShadowDecorator(title);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(title);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);


        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(anim);
        ViewTime.getInstance().register(()->{
            createGreyBackground();
            vEngine.getManager().addView(view);
        },0);

        controller.setMenuState(view.getMenuViewContainer());
    }
    public void createGreyBackground(){
            SwappableView view = new SwappableView();
            BackgroundImageDrawable background = new BackgroundImageDrawable(vEngine);
            view.addDrawable(background);
            vEngine.getManager().addView(view);
    }

    public void createTradeView(NPC npc, Character player, boolean active){

        /*
        Creates 2 Navigatable grids that have Titles
         */
        NavigatableGridWithCenterTitle npcInv = new NavigatableGridWithCenterTitle(250, 400, 5, 5,
                Config.TEAL,
                Config.MEDIUMGREY,
                Config.DARKGREY,
                "Shopkeeper Gold: " + npc.getItemStorage().getMoney().getValue()
        );
        NavigatableGridWithCenterTitle playerInv = new NavigatableGridWithCenterTitle(250, 400, 5, 5,
                Config.TEAL,
                Config.MEDIUMGREY,
                Config.DARKGREY,
                "Your Gold: " + player.getItemStorage().getMoney().getValue()
        );

        BuyableUIObjectCreationVisitor visitor = new BuyableUIObjectCreationVisitor(this, npc, player, npcInv, playerInv);
        visitor.visitBoth();
        //make menu
        npcInv.setList(visitor.getShopOwnerInvList());

        playerInv.setList(visitor.getPlayerInvList());

        //make swappable view
        SwappableView swappableView = new SwappableView();
        //add decorators to center the menu
        ColumnatedCompositeContainer columns  = new ColumnatedCompositeContainer();
        columns.setHeight(400);
        columns.setWidth(700);


        VerticalCenterContainer viewTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(columns, "Buy / Sell")
                        )
                );

        VerticalCenterContainer npcTradeTitle = new VerticalCenterContainer(
                new HorizontalCenterContainer(npcInv.getTitleBar()));

        VerticalCenterContainer playerTradeTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(playerInv.getTitleBar()));

        columns.addDrawable(npcTradeTitle);
        columns.addDrawable(playerTradeTitle);



        swappableView.addDrawable(viewTitle);

        swappableView.addNavigatable(npcInv);
        swappableView.addNavigatable(playerInv);


        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(swappableView);
        },0);

        controller.setMenuState(swappableView.getMenuViewContainer());
        //This ACTIVE boolean serves the purpose of knowing whether or not draw the selector in the buy window
        //or sell window... It's probably a huge hack and introduces alternate cohesion... :O Blame John
        if(!active) {
            swappableView.getMenuViewContainer().swap();
        }
    }

    //Triggers initial animation dialog window - afterwards, continue is used.
    public void createDialogView(NPC npc, Character player, Iterator dialogIter){
        String dialog = (String) dialogIter.next();
        ScrollableMenuItem textHolder = new ScrollableMenuItem(dialog, null);
        NavigatableList chatOptions = new NavigatableList();
        ScrollableMenuItem continueButton = new ScrollableMenuItem("Continue",()->{
                if(dialogIter.hasNext()) {
                    textHolder.setName((String) dialogIter.next());
                }
                if(!dialogIter.hasNext()) {
                    chatOptions.removeItemFromIndex(0);
                }
        });
        chatOptions.addItem(continueButton);

        chatOptions.addItem(new ScrollableMenuItem("Exit",()->{
            exitMenu();
        }));

        ScrollableMenu chatMenu = new ScrollableMenu(300,400);
        chatMenu.setList(chatOptions);

        NavigatableList conversation = new NavigatableList();
        conversation.addItem(textHolder);

        ScrollableMenu conversationMenu = new ScrollableMenu(300,400);
        conversationMenu.setList(conversation);

        RowedCompositeContainer rows = new RowedCompositeContainer();
        rows.setWidth(400);
        rows.setHeight(300);
        rows.addDrawable(conversationMenu);
        rows.addDrawable(chatMenu);

        TitleBarDecorator title = new TitleBarDecorator(rows, "Conversation", Config.TEAL);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(title);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);

        SwappableView view = new SwappableView();
        view.addNavigatable(chatMenu);
        view.addDrawable(anim);

        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());

    }


    public ScrollableMenuItem createLoadMenu(ScrollableMenu menu, NavigatableList list){
        return new ScrollableMenuItem("Load", ()->{
            NavigatableList loadList = new NavigatableList();
            loadList.addItem(new ScrollableMenuItem("Slot 1", ()->{
                LoadGameLauncher loadGameLauncher = new LoadGameLauncher(controller, mEngine, vEngine);
                File file = new File(PATH + "save1.xml");
                LoadFromXMLFile loader = new LoadFromXMLFile(
                        loadGameLauncher,
                        file
                );
                loader.loadGame();
                loadGameLauncher.launch();
                resumeGame();
                exitMenu();
            }));
            loadList.addItem(new ScrollableMenuItem("Slot 2", ()->{
                LoadGameLauncher loadGameLauncher = new LoadGameLauncher(controller, mEngine, vEngine);
                File file = new File(PATH + "save2.xml");
                LoadFromXMLFile loader = new LoadFromXMLFile(
                        loadGameLauncher,
                        file
                );
                loader.loadGame();
                loadGameLauncher.launch();
                resumeGame();
                exitMenu();
            }));
            loadList.addItem(new ScrollableMenuItem("Slot 3", ()->{
                LoadGameLauncher loadGameLauncher = new LoadGameLauncher(controller, mEngine, vEngine);
                File file = new File(PATH + "save3.xml");
                LoadFromXMLFile loader = new LoadFromXMLFile(
                    loadGameLauncher,
                    file
                );
                loader.loadGame();
                loadGameLauncher.launch();
                resumeGame();
                exitMenu();
            }));
            loadList.addItem(new ScrollableMenuItem("Back", ()->{
                menu.setList(list);
            }));
            menu.setList(loadList);
        });
    }

    public ScrollableMenuItem createSaveMenu(ScrollableMenu menu, NavigatableList list){
        return new ScrollableMenuItem("Save", ()->{
            NavigatableList loadList = new NavigatableList();
            loadList.addItem(new ScrollableMenuItem("Slot 1", ()->{

                new SaveToXMLFile("save1.xml").saveGame();
                exitMenu();
                createToast(5, "Successful save to Slot 1");
            }));
            loadList.addItem(new ScrollableMenuItem("Slot 2", ()->{

                new SaveToXMLFile("save2.xml").saveGame();
                exitMenu();
                createToast(5, "Successful save to Slot 2");
            }));
            loadList.addItem(new ScrollableMenuItem("Slot 3", ()->{

                new SaveToXMLFile("save3.xml").saveGame();
                exitMenu();
                createToast(5, "Successful save to Slot 3");
            }));
            loadList.addItem(new ScrollableMenuItem("Back", ()->{
                menu.setList(list);
            }));
            menu.setList(loadList);
        });
    }

    public void exitMenu() {
        resumeGame();
        this.getController().setPlayState();
        ViewTime.getInstance().register(()->{
            this.getController().clearViews();
        },0);

    }

    public void createKeyBindMenu(ControllerState state){

        ScrollableMenu menu = new ScrollableMenu(400,500);
        NavigatableList list = new NavigatableList();

        java.util.Map<ActionEnum, Integer> map = state.getMappings();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            list.addItem(new ScrollableMenuItem(pair.getKey() + " ---> " + pair.getValue(), ()->{
                //do something
                SwappableView view = new SwappableView();
                view.addDrawable(new VerticalCenterContainer(new HorizontalCenterContainer(new KeyBindView(40,200))));
                ViewTime.getInstance().register(()->{
                    vEngine.getManager().addView(view);
                },0);
                //controller.setMenuState(view.getMenuViewContainer());

            }));
        }
        menu.setList(list);
        TitleBarDecorator title = new TitleBarDecorator(menu,"Rebind Keys", Config.TEAL);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(title);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);

        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(vert);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());

    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public JFrame getJframe() {
        return jframe;
    }

    public void setJframe(JFrame jframe) {
        this.jframe = jframe;
    }

    public ViewEngine getvEngine() {
        return vEngine;
    }

    public void setvEngine(ViewEngine vEngine) {
        this.vEngine = vEngine;
    }

    public ModelEngine getmEngine() {
        return mEngine;
    }

    public void setmEngine(ModelEngine mEngine) {
        this.mEngine = mEngine;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void createToast(int dur, String msg) {
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addToast( new Toast(dur, msg));
        },0);
    }

    public void pauseGame(){
        ModelTime.getInstance().pause();
        ViewTime.getInstance().pause();
    }

    public void resumeGame(){
        ModelTime.getInstance().resume();
        ViewTime.getInstance().resume();
    }

    public void resetGame() {
        ModelTime.getInstance().reset();
        ViewTime.getInstance().reset();
    }

}
