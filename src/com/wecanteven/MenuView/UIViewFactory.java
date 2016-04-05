package com.wecanteven.MenuView;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.GameLaunchers.LoadGameLauncher;
import com.wecanteven.GameLaunching.GameLaunchers.NewGameLauncher;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridItem;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.NavigatableGrid;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenu;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenuItem;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
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
    MainController controller;
    ViewEngine vEngine;
    ModelEngine mEngine;
    Avatar avatar;

    private static UIViewFactory ourInstance = new UIViewFactory();

    private UIViewFactory(){};

    public static UIViewFactory getInstance(){return ourInstance;}

    public void setJFrame(JFrame jFrame){
        this.jframe = jFrame;
    }

    public SwappableView createInventoryView(){
        //make menu
        NavigatableGrid menu = new NavigatableGrid(400, 400, 3, 3);
        //menu.setSelectedColor(Color.cyan);
        //make menu list
        NavigatableList list = new NavigatableList();
        list.addItem(new GridItem("New Game", () -> {System.out.println("test 1 selected");}));
        list.addItem(new GridItem("Load Game", () -> {System.out.println("test 2 selected");}));
        list.addItem(new GridItem("Exit", () -> {System.out.println("test 2 selected");}));
        list.addItem(new GridItem("bloop", () -> {System.out.println("test 1 selected");}));
        list.addItem(new GridItem("Lsdfdme", () -> {System.out.println("test 2 selected");}));
        list.addItem(new GridItem("E55555it", () -> {System.out.println("test 2 selected");}));
        list.addItem(new GridItem("Nddddddde", () -> {System.out.println("test 1 selected");}));

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
                    //loadGameLauncher.launch();
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
