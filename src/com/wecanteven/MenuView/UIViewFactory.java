package com.wecanteven.MenuView;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.GameLaunching.GameLaunchers.NewGameLauncher;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.TitleBarDecorator;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.ColumnatedCompositeContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.CustomScaleColumnsContainer;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGrid;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.*;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;
import com.wecanteven.SaveLoad.Load.LoadFromXMLFile;
import com.wecanteven.ViewEngine;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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

    private UIObjectCreationVisitor visitor = new UIObjectCreationVisitor(this);

    private static UIViewFactory ourInstance = new UIViewFactory();

    private UIViewFactory(){};

    public static UIViewFactory getInstance(){return ourInstance;}

    public void setJFrame(JFrame jFrame){
        this.jframe = jFrame;
    }

    public void createStatsView(Character character){


        NavigatableList skillList = new NavigatableList();
        skillList.addItem(new ScrollableMenuItem("placeholder skill", ()->{
            System.out.println("you tried to level up palcehold skill kappafacenospace");
        }));

        ScrollableMenu skillMenu = new ScrollableMenu(300,600);
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
        columns.setHeight(500);
        columns.addDrawable(menu);
        columns.addDrawable(skillMenu);

        TitleBarDecorator title = new TitleBarDecorator(columns, "Skills/Stats");
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(title);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);

        SwappableView view = new SwappableView();
        view.addNavigatable(skillMenu);
        view.addDrawable(vert);

        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());


    }

    public void createInventoryView(Character character){
        character.accept(visitor);
        NavigatableList list = visitor.getInventoryItems();
        NavigatableList equiplist = visitor.getEquippedItems();
        //make menu
        NavigatableGrid menu = new NavigatableGrid(400, 400, 5, 5);
        menu.setBgColor(new Color(90,70,50));

        NavigatableGrid equipMenu = new NavigatableGrid(100, 400, 1, 4);
        equipMenu.setBgColor(new Color(60,50,60));

        //NavigatableList equiplist = new NavigatableList();
        //equiplist.addItem();

        menu.setList(list);
        equipMenu.setList(equiplist);
        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu
        CustomScaleColumnsContainer columns  = new CustomScaleColumnsContainer(new int[]{4,1});
        columns.setHeight(400);
        columns.setWidth(700);
        columns.addDrawable(menu);
        columns.addDrawable(equipMenu);

        TitleBarDecorator title = new TitleBarDecorator(columns, "Inventory/Equipment");
        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(title);
        VerticalCenterContainer vertCenter = new VerticalCenterContainer(horizCenter);
//        view.addDrawable(vertCenter);


        view.addDrawable(vertCenter);
        view.addNavigatable(menu);
        view.addNavigatable(equipMenu);
        //return created swappable view

        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());
    }

    public SwappableView createHUDView(){
        return null; //TODO: implement
    }
    public SwappableView createAbilityView(){
        return null; //TODO: implement
    }
    public SwappableView createMainMenuView(){
        //make menu
        ScrollableMenu menu = new ScrollableMenu(400, 400);
        menu.setSelectedColor(Color.cyan);
        //make menu list
        NavigatableList list = new NavigatableList();
        list.addItem(
                new ScrollableMenuItem("New Game", () -> {
                    NewGameLauncher template = new NewGameLauncher(controller, mEngine, vEngine);
                    template.launch();
            })
        );
        list.addItem(
                new ScrollableMenuItem("Load Game", () -> {
                    System.out.println("Load Game was selected");
                    //TODO: Be moved to a Load Screen - Give the player the option to choose a file and
                    //TODO: create the Launcher and Loader from that file selection
                    LoadGameLauncher loadGameLauncher = new LoadGameLauncher(controller, mEngine, vEngine);
                    File file = new File(PATH + "save1.xml");
                    LoadFromXMLFile loader = new LoadFromXMLFile(
                            loadGameLauncher,
                            file
                    );
                    loader.loadGame();
                    loadGameLauncher.launch();
            })
        );
        list.addItem(new ScrollableMenuItem("Exit", () -> {System.out.println("test 2 selected");}));
        menu.setList(list);
        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu
        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vertCenter = new VerticalCenterContainer(horizCenter);
        view.addDrawable(vertCenter);
        view.addNavigatable(menu);
        //return created swappable view
        return view;
    }

    public void createEquippableItemMenu(Character character, EquipableItem item){
        NavigatableList list = new NavigatableList();
        list.addItem(new ScrollableMenuItem("Equip", () ->{
            System.out.println("equip pressed");
            character.equipItem(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                createInventoryView(avatar.getCharacter());
            },0);

        }));
        list.addItem(new ScrollableMenuItem("Drop", () ->{
            System.out.println("drop pressed");
            character.drop(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                createInventoryView(avatar.getCharacter());
            },0);
        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{
            System.out.println("cancel pressed");
            ViewTime.getInstance().register(() ->{
                controller.popView();
                createInventoryView(avatar.getCharacter());
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
    public void createEquippedItemMenu(Character character, EquipableItem item){
        NavigatableList list = new NavigatableList();
        list.addItem(new ScrollableMenuItem("Unequip", () ->{
            System.out.println("unequip pressed");
            character.unequipItem(item);
            ViewTime.getInstance().register(() ->{
                controller.popView();
                createInventoryView(avatar.getCharacter());
            },0);

        }));
        list.addItem(new ScrollableMenuItem("Cancel", () ->{
            System.out.println("cancel pressed");
            ViewTime.getInstance().register(() ->{
                controller.popView();
                createInventoryView(avatar.getCharacter());
            },0);
        }));
        ScrollableMenu menu = new ScrollableMenu(100,70);
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
    public SwappableView createUsableItemMenu(){
        return null;
    }
    public SwappableView createConsumableItemMenu(){
        return null;
    }
    public SwappableView createAbilityItemMenu(){
        return null;
    }


    public void createTradeView(Character npc, Character player, int bargainLevel){
        npc.accept(visitor);
        NavigatableList npcList = visitor.getInventoryItems();
        player.accept(visitor);
        NavigatableList playerList = visitor.getInventoryItems();

        //make menu
        NavigatableGrid npcInv = new NavigatableGrid(250, 400, 5, 5);
        npcInv.setBgColor(new Color(90,70,50));

        NavigatableGrid playerInv = new NavigatableGrid(250, 400, 5, 5);
        playerInv.setBgColor(new Color(90,70,70));

        npcInv.setList(npcList);
        playerInv.setList(playerList);
        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu
        ColumnatedCompositeContainer columns  = new ColumnatedCompositeContainer();
        columns.setHeight(400);
        columns.setWidth(700);



        VerticalCenterContainer npcTradeTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(npcInv, "NPC Inventory")
                        )
                );
        columns.addDrawable(npcTradeTitle);

        VerticalCenterContainer playerTradeTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(playerInv, "NPC Your Inventory")
                        )
                );

        columns.addDrawable(playerTradeTitle);

        VerticalCenterContainer title =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(columns, "Trade")
                        )
                );

        view.addDrawable(title);


        view.addNavigatable(npcInv);
        //view.addNavigatable(playerInv);


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
}
