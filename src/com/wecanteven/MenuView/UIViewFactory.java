package com.wecanteven.MenuView;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Controllers.AIControllers.AITime;
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
import com.wecanteven.MenuView.DrawableLeafs.HUDview.HUDview;
import com.wecanteven.MenuView.DrawableLeafs.HUDview.StatsHUD;
import com.wecanteven.MenuView.DrawableLeafs.KeyBindView;

import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.*;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.*;
import com.wecanteven.MenuView.DrawableLeafs.Toaster.Toast;
import com.wecanteven.MenuView.UIObjectCreationVisitors.AbilityViewObjectCreationVisitor;
import com.wecanteven.MenuView.UIObjectCreationVisitors.BuyableUIObjectCreationVisitor;
import com.wecanteven.MenuView.UIObjectCreationVisitors.EquippableUIObjectCreationVisitor;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Interactions.DialogInteractionStrategy;
import com.wecanteven.Models.Interactions.TradeInteractionStrategy;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Occupation.Sneak;
import com.wecanteven.Models.Occupation.Summoner;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.SaveLoad.Load.LoadFromXMLFile;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Sound;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.ViewEngine;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

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
        //StatsHUD statsHUD = new StatsHUD(character.getStats());
        HUDview statsHUD = new HUDview();
        //HorizontalCenterContainer horiz = new HorizontalCenterContainer(statsHUD);
//        statsHUD.setHeight(150);
//        statsHUD.setWidth(300);
        //statsHUD.setBgColor(new Color(.5f,.5f,.5f,.5f));
        SwappableView view = new SwappableView();
        view.addDrawable(statsHUD);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addPermView(view);
        },0);
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
                    controller.popView();
                    createCreateCharacterMenuView();

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

    public void createCreateCharacterMenuView() {

        ColumnatedCompositeContainer columns = new ColumnatedCompositeContainer();
        columns.setHeight(500);
        columns.setWidth(800);

        NavigatableGrid characterView = new NavigatableGrid(400, 400, 1, 1);
        NavigatableGrid classSelection = new NavigatableGrid(300, 100, 3, 1);
        NavigatableGrid faceSelection = new NavigatableGrid(300, 100, 2, 1);
        NavigatableGrid colorSelection = new NavigatableGrid(300, 100, 4, 1);
        ScrollableMenu startGameSelection = new ScrollableMenu(400, 400);

        characterView.setBgColor(Config.TRANSMEDIUMGREY);
        classSelection.setBgColor(Config.TRANSMEDIUMGREY);
        faceSelection.setBgColor(Config.TRANSMEDIUMGREY);
        colorSelection.setBgColor(Config.TRANSMEDIUMGREY);
        startGameSelection.setBgColor(Config.TRANSMEDIUMGREY);
        //make menu list
        NavigatableList characterList = new NavigatableList();
        NavigatableList classList = new NavigatableList();
        NavigatableList faceList = new NavigatableList();
        NavigatableList colorList = new NavigatableList();
        NavigatableList startList = new NavigatableList();


        characterView.setList(characterList);
        classSelection.setList(classList);
        faceSelection.setList(faceList);
        colorSelection.setList(colorList);
        startGameSelection.setList(startList);

        //make swappable view
        SwappableView view = new SwappableView();
        //add decorators to center the menu

        view.addNavigatable(colorSelection);
        view.addNavigatable(faceSelection);
        view.addNavigatable(classSelection);
        view.addNavigatable(startGameSelection);

        RowedCompositeContainer selectRow = new RowedCompositeContainer(500, 450);


        selectRow.addDrawable(colorSelection);
        selectRow.addDrawable(faceSelection);
        selectRow.addDrawable(classSelection);
        selectRow.addDrawable(startGameSelection);



        TitleBarDecorator title = new TitleBarDecorator(selectRow,"Select your Color:", Config.GRAY);
        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(title);
        VerticalCenterContainer vertCenter = new VerticalCenterContainer(horizCenter);

        view.addDrawable(vertCenter);

        TitleBarDecorator previewTitle = new TitleBarDecorator(characterView,"Preview", Config.GRAY);
        HorizontalCenterContainer previewTitleH = new HorizontalCenterContainer(previewTitle);
        VerticalCenterContainer previewTitleV = new VerticalCenterContainer(previewTitleH);

        SwappableView dualView = new SwappableView();
        columns.addDrawable(previewTitleV);
        columns.addDrawable(title);


        //MAIN WINDOW TITLE
        TitleBarDecorator mainView = new TitleBarDecorator(columns,"Create Character", Config.DARK_GRAY);
        HorizontalCenterContainer mainViewC = new HorizontalCenterContainer(mainView);
        VerticalCenterContainer mainViewV = new VerticalCenterContainer(mainViewC);

        dualView.addDrawable(mainViewV);

        controller.setMainMenuState(view.getMenuViewContainer());

        ViewTime.getInstance().register(()->{
            this.getController().clearViews();
            this.getvEngine().clear();
            vEngine.getManager().addView(dualView);
        },0);

        GridPreview preview = new GridPreview(() -> {

        });
        characterList.addItem(preview);

        //THIS IS SO HACKY! HELP
        Object menuItems[] = new Object[3];
        Occupation occupation;
        classList.addItem(new GridString("Smasher", ()->{
            menuItems[0] = new Smasher();
            view.getMenuViewContainer().swap();
            classList.setCurrentIndex(0);
            classSelection.setActive(true);
            title.setTitle("Start Game!");
        }));
        classList.addItem(new GridString("Sneak", ()->{
            menuItems[0] = new Sneak();
            view.getMenuViewContainer().swap();
            classList.setCurrentIndex(1);
            classSelection.setActive(true);
            title.setTitle("Start Game!");
        }));
        classList.addItem(new GridString("Summoner", ()->{
            menuItems[0] = new Summoner();
            view.getMenuViewContainer().swap();
            classList.setCurrentIndex(2);
            classSelection.setActive(true);
            title.setTitle("Start Game!");
        }));


        faceList.addItem(new GridFace("Connery", () -> {
            //set
            menuItems[1] = "Connery";
            preview.setFace("Connery");
            view.getMenuViewContainer().swap();
            faceList.setCurrentIndex(0);
            faceSelection.setActive(true);
            title.setTitle("Select your Class:");
        }));
        faceList.addItem(new GridFace("Test Face", () -> {
            //set Face
            menuItems[1] = "TestFace";
            preview.setFace("Test Face");
            view.getMenuViewContainer().swap();
            faceList.setCurrentIndex(1);
            faceSelection.setActive(true);
            title.setTitle("Select your Class:");

        }));

        //CREATING LIST
        colorList.addItem(new GridColor(GameColor.BLUE, () -> {
            //setColor to ace
            menuItems[2] = GameColor.BLUE;
            preview.setColor(GameColor.BLUE);
            mainView.setBgColor(GameColor.BLUE.dark);
            title.setBgColor(GameColor.BLUE.light);
            previewTitle.setBgColor(GameColor.BLUE.light);
            view.getMenuViewContainer().swap();
            colorList.setCurrentIndex(0);
            colorSelection.setActive(true);
            title.setTitle("Choose your Face:");
        }));
        colorList.addItem(new GridColor(GameColor.GREEN, () -> {
            //setColor to ace
            menuItems[2] = GameColor.GREEN;
            preview.setColor(GameColor.GREEN);
            mainView.setBgColor(GameColor.GREEN.dark);
            title.setBgColor(GameColor.GREEN.light);
            previewTitle.setBgColor(GameColor.GREEN.light);
            view.getMenuViewContainer().swap();
            colorList.setCurrentIndex(1);
            colorSelection.setActive(true);
            title.setTitle("Choose your Face:");

        }));
        colorList.addItem(new GridColor(GameColor.PINK, () -> {
            //setColor to ace
            menuItems[2] = GameColor.PINK;
            preview.setColor(GameColor.PINK);
            mainView.setBgColor(GameColor.PINK.dark);
            title.setBgColor(GameColor.PINK.light);
            previewTitle.setBgColor(GameColor.PINK.light);
            view.getMenuViewContainer().swap();
            colorList.setCurrentIndex(2);
            colorSelection.setActive(true);
            title.setTitle("Choose your Face:");

        }));
        colorList.addItem(new GridColor(GameColor.YELLOW, () -> {
            //setColor to ace
            menuItems[2] = GameColor.YELLOW;
            preview.setColor(GameColor.YELLOW);
            mainView.setBgColor(GameColor.YELLOW.dark);
            title.setBgColor(GameColor.YELLOW.light);
            previewTitle.setBgColor(GameColor.YELLOW.light);
            view.getMenuViewContainer().swap();
            colorList.setCurrentIndex(3);
            colorSelection.setActive(true);
            title.setTitle("Choose your Face:");

        }));

        //START GAME LIST
        startList.addItem(
                new ScrollableMenuItem("Start Game", () -> {
                    if(menuItems[0] == null || menuItems[1] == null || menuItems[2] == null) {view.getMenuViewContainer().swap(); return; }
                    Sound.play("startGame");
                    NewGameLauncher template = new NewGameLauncher(controller, mEngine, vEngine, (Occupation)menuItems[0], (String)menuItems[1], (GameColor)menuItems[2]);
                    Sound.stopAll();
                    template.launch();
                    resumeGame();
                })
        );
        startList.addItem(
                new ScrollableMenuItem("Restart", () -> {
                    menuItems[0] = null;
                    menuItems[1] = null;
                    menuItems[2] = null;
                    preview.setColor(GameColor.GRAY);
                    mainView.setBgColor(GameColor.GRAY.dark);
                    title.setBgColor(GameColor.GRAY.light);
                    previewTitle.setBgColor(GameColor.GRAY.light);
                    faceSelection.setActive(false);
                    classSelection.setActive(false);
                    colorSelection.setActive(false);
                    view.getMenuViewContainer().swap();
                    title.setTitle("Select your Color:");
                    startList.setCurrentIndex(0);
                })
        );

    }

    public void createSkillList(Character character, NavigatableList list, TitleBarDecorator title) {
        title.setTitle("Available Points: " + character.getAvailablePoints());

        Iterator<Tuple<Skill, Integer>> iter = character.getOccupation().getSkillIterator();
        list.clear();
        while (iter.hasNext()) {
            Tuple<Skill, Integer> skill = iter.next();
            list.addItem(new ScrollableMenuItem(skill.x + ": " + skill.y, () -> {
                if (!character.allocateSkillPoint(skill.x, 1)) {
                    createToast(2, "You're out of skill points!");
                }
                createSkillList(character, list, title);
            }));
        }
    }

    public void createStatsView(Character character){

        NavigatableList skillsList = new NavigatableList();

        ScrollableMenu skillMenu = new ScrollableMenu(300,650);

        TitleBarDecorator skillTitle = new TitleBarDecorator(
                skillMenu, "Available Points: " + character.getAvailablePoints(), character.getColor().light);

        HorizontalCenterContainer horizSkill = new HorizontalCenterContainer(skillTitle);
        VerticalCenterContainer vertSkill = new VerticalCenterContainer(horizSkill);

        createSkillList(character, skillsList, skillTitle);
        skillMenu.setList(skillsList);



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
        columns.addDrawable(skillTitle);

        TitleBarDecorator title = new TitleBarDecorator(columns, "Skills/Stats", character.getColor().dark);
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

        TitleBarDecorator title = new TitleBarDecorator(columns, "Inventory/Equipment", character.getColor().dark);
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
            pauseGame();
        },0);
        controller.setMenuState(view.getMenuViewContainer());
    }
    public void createAbilityView(Character character){
        NavigatableGrid menu = new NavigatableGrid(400, 400, 5, 5);
        NavigatableGrid equipMenu = new NavigatableGrid(100, 400, 1, 4);
        AbilityViewObjectCreationVisitor visitor = new AbilityViewObjectCreationVisitor(this, menu, equipMenu); //add params

        character.accept(visitor);
        NavigatableList list = visitor.getInventory();
        NavigatableList equiplist = visitor.getEquipped();

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

        TitleBarDecorator title = new TitleBarDecorator(columns, "Abilities", character.getColor().dark);
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
    public void createDropableItemMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, TakeableItem item){
        EquippableUIObjectCreationVisitor visitor = new EquippableUIObjectCreationVisitor(this,invHolder,eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
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
        ScrollableMenu menu = new ScrollableMenu(100,70);
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
    public void createUsableItemMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, UseableItem item){
        EquippableUIObjectCreationVisitor visitor = new EquippableUIObjectCreationVisitor(this,invHolder,eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Use", () ->{

            character.consume(item);
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

    public void createAbilityMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, Ability ability) {
        AbilityViewObjectCreationVisitor visitor = new AbilityViewObjectCreationVisitor(this,invHolder,eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Equip", () -> {
            character.equipAbility(ability);
            ViewTime.getInstance().register(() -> {
                controller.popView();
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventory());
                eqHolder.setList(visitor.getEquipped());
            }, 0);
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

    public void createEquippedAbilityMenu(Character character, NavigatableListHolder invHolder, NavigatableListHolder eqHolder, Ability ability) {
        AbilityViewObjectCreationVisitor visitor = new AbilityViewObjectCreationVisitor(this,invHolder,eqHolder);
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Unequip", () -> {
            character.unequipAbility(ability);
            ViewTime.getInstance().register(() -> {
                controller.popView();
                visitor.visitCharacter(character);
                invHolder.setList(visitor.getInventory());
                eqHolder.setList(visitor.getEquipped());
            }, 0);
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

    //WHEN THE SHOPPERKEEPER TRIES TO SELL TO THE SHOPPER
    public void createBuyableItemMenu(BuyableUIObjectCreationVisitor visitor, NPC shopOwner, Character buyer, TakeableItem item){
        int bargainLevel = buyer.getSkillPoints(Skill.BARGAIN);
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
        ScrollableMenu menu = new ScrollableMenu(130,70);
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
        int bargainLevel = buyer.getSkillPoints(Skill.BARGAIN) + 1;

        NavigatableList list = new NavigatableList();
        TradeInteractionStrategy interactionStrategy = (TradeInteractionStrategy) shopOwner.getInteraction();
        list.addItem(new ScrollableMenuItem("Sell: " + (((float)item.getValue()*(1f/(float)bargainLevel)) + item.getValue()) + " Gold", () ->{

            if(!shopOwner.getItemStorage().inventoryIsFull() && interactionStrategy.buy(item)){
                buyer.getItemStorage().removeItem(item);
                shopOwner.pickup(item);
                createToast(3, "You've sold a " + item.getName() + " for " + (((float)item.getValue()*(1f/(float)bargainLevel)) + item.getValue()) + " gold!");
            }else {
                //SHOPOWNER CANT BUY IF HIS INVENTORY IS FULL
                if(shopOwner.getItemStorage().inventoryIsFull()){
                    createToast(3, "Shop Owner's inventory is full!");

                }else {
                    //SHOPOWNER CANT BUY IF HE DOESNT HAVE MONEY
                    createToast(5, "The Shopkeeper can't afford a " + item.getName() + " for " + (((float)item.getValue()*(1f/(float)bargainLevel)) + item.getValue()) + " gold!");
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
        ScrollableMenu menu = new ScrollableMenu(130,70);
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

        NavigatableList windowOptions = new NavigatableList();
        ScrollableMenuItem soundOpt =  new ScrollableMenuItem("Toggle Sound", ()->{
            Sound.toggleMute();
        });
        windowOptions.addItem(soundOpt);
        windowOptions.addItem(new ScrollableMenuItem("Fullscreen", ()->{

            ViewTime.getInstance().register(()->{
                vEngine.dispose();
                vEngine.setUndecorated(true);
                GraphicsDevice device = GraphicsEnvironment
                        .getLocalGraphicsEnvironment().getScreenDevices()[0];
                device.setFullScreenWindow(vEngine);
                vEngine.pack();
                vEngine.setExtendedState(vEngine.MAXIMIZED_BOTH);


            },0);
        }));
        windowOptions.addItem(new ScrollableMenuItem("Windowed", ()->{
            ViewTime.getInstance().register(()->{
                vEngine.dispose();
                vEngine.setUndecorated(false);
                GraphicsDevice device = GraphicsEnvironment
                        .getLocalGraphicsEnvironment().getScreenDevices()[0];
                device.setFullScreenWindow(vEngine);
                vEngine.setPreferredSize(new Dimension(1280,720));
                vEngine.pack();
            },0);
        }));
        windowOptions.addItem(new ScrollableMenuItem("Back", ()->{
            ViewTime.getInstance().register(()->{
               menu.setList(list);
            },0);
        }));
        list.addItem(new ScrollableMenuItem("Options", ()->{
            ViewTime.getInstance().register(()->{
                menu.setList(windowOptions);
            },0);
        }));
        list.addItem(new ScrollableMenuItem("Exit to Main Menu", ()->{

            ViewTime.getInstance().register(()->{
                createMainMenuView();
            },0);
        }));


        menu.setList(list);
        TitleBarDecorator title = new TitleBarDecorator(menu,"Pause Menu", controller.getAvatar().getCharacter().getColor().dark);
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
                                new TitleBarDecorator(columns, "Buy / Sell", player.getColor().dark)
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
        //or sell window... It's probably a huge hack and introduces alternate cohesion... :O Blame Josh
        if(!active) {
            swappableView.getMenuViewContainer().swap();
        }
    }

    public void createPickPocketView(Character attacker, Character attackee, int ppSkillLevel){


        NavigatableGrid inventory = new NavigatableGrid(400, 400, 5, 3);
        inventory.setBgColor(Config.TRANSMEDIUMGREY);
        NavigatableList inventoryItems = new NavigatableList();
        inventory.setList(inventoryItems);

        Iterator invIter = attackee.getItemStorage().getInventory().getIterator();
        //LIST OF ITEMS IN HIS INVENTORY
        while(invIter.hasNext()){
            TakeableItem i = (TakeableItem) invIter.next();
            inventoryItems.addItem(new GridItem(i.getName() ,()->{
                createPickPocketItemView(attacker, attackee, i, ppSkillLevel);

            }));
        }



        VerticalCenterContainer viewTitle =
                new VerticalCenterContainer(
                        new HorizontalCenterContainer(
                                new TitleBarDecorator(inventory, "Pickpocket", attacker.getColor().dark)
                        )
                );

        SwappableView view = new SwappableView();
        view.addDrawable(viewTitle);
        view.addNavigatable(inventory);

        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);

        controller.setMenuState(view.getMenuViewContainer());

    }

    public void createPickPocketItemView(Character attacker, Character attackee, TakeableItem item, int ppSkillLevel){
        NavigatableList list = new NavigatableList();
        MenuViewContainer container = controller.getMenuState().getMenus();
        list.addItem(new ScrollableMenuItem("Attempt to Pickpocket", () ->{

            ViewTime.getInstance().register(() ->{
                controller.popView();
                exitMenu();
                //Skill Level, Item Value, RandomNumber
                int itemChance = item.getValue()/20;
                int skillChance = ppSkillLevel*10;
                int failChance = 90;
                failChance -= skillChance + itemChance;

                Random randomGenerator = new Random();
                if(!attacker.getItemStorage().inventoryIsFull() && failChance <= randomGenerator.nextInt(100)){
                    //Success
                    attackee.getItemStorage().removeItem(item);
                    attacker.getItemStorage().addItem(item);
                    createToast(3, "You stole: " + item.getName());
                }else{
                    //failed
                    createToast(3, "You failed at Pickpocketing");
                }

            },0);
            controller.setMenuState(container);
        }));

        ScrollableMenu menu = new ScrollableMenu(200,50);
        HorizontalCenterContainer horiz = new HorizontalCenterContainer(menu);
        VerticalCenterContainer vert = new VerticalCenterContainer(horiz);
        AnimatedCollapseDecorator anim = new AnimatedCollapseDecorator(vert);
        menu.setBgColor(Config.PAPYRUS);
        menu.setList(list);
        SwappableView view = new SwappableView();
        view.addNavigatable(menu);
        view.addDrawable(anim);
        ViewTime.getInstance().register(()->{
            vEngine.getManager().addView(view);
        },0);
        controller.setMenuState(view.getMenuViewContainer());
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

        TitleBarDecorator title = new TitleBarDecorator(rows, "Conversation", player.getColor().dark);
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

        ScrollableMenu menu = new ScrollableMenu(400,600);
        NavigatableList list = new NavigatableList();

        java.util.Map<ActionEnum, Integer> map = state.getMappings();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            list.addItem(new ScrollableMenuItem(pair.getKey() + " ---> " + (char)(int)pair.getValue(), ()->{
                //do something
                SwappableView view = new SwappableView();
                view.addDrawable(new VerticalCenterContainer(new HorizontalCenterContainer(new KeyBindView(40,200))));
                ViewTime.getInstance().register(()->{
                    vEngine.getManager().addView(view);
                },0);
                controller.setKeyBindState(map,(ActionEnum) pair.getKey());
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
        AITime.getInstance().pause();
    }

    public void resumeGame(){
        if(!Sound.mute){
            Sound.play("gameTheme");
        }
        ModelTime.getInstance().resume();
        ViewTime.getInstance().resume();
        AITime.getInstance().resume();
    }

    public void resetGame() {
        ModelTime.getInstance().reset();
        ViewTime.getInstance().reset();
    }
}
