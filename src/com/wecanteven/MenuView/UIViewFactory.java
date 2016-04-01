package com.wecanteven.MenuView;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.RowedCompositeContainer;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenu;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenuItem;
import com.wecanteven.ViewEngine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class UIViewFactory {
    private JFrame jframe;

    public UIViewFactory(JFrame jFrame){
        this.jframe = jFrame;
    }

    public SwappableView createInventoryView(){
        return null;//TODO: implement
    }
    public SwappableView createHUDView(){
        return null; //TODO: implement
    }
    public SwappableView createAbilityView(){
        return null; //TODO: implement
    }
    public SwappableView mainMenuView(){
        //make menu
        ScrollableMenu menu = new ScrollableMenu(400, 400);
        menu.setSelectedColor(Color.cyan);
        //make menu list
        NavigatableList list = new NavigatableList();
        list.addItem(new ScrollableMenuItem("New Game", () -> {System.out.println("test 1 selected");}));
        list.addItem(new ScrollableMenuItem("Load Game", () -> {System.out.println("test 2 selected");}));
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
}
