package com.wecanteven;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.RowedCompositeContainer;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenu;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenuItem;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.MenuView.SwingToDrawableAdapter;
import com.wecanteven.MenuView.ViewManager;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello world");

        ScrollableMenu menu = new ScrollableMenu(300,300);
        ScrollableMenu menu2 = new ScrollableMenu(300,300);

        NavigatableList list = new NavigatableList();
        list.addItem(new ScrollableMenuItem("test 1", () -> {System.out.println("test 1 selected");}));
        list.addItem(new ScrollableMenuItem("test 2", () -> {System.out.println("test 2 selected");}));
        menu.setList(list);

        NavigatableList list2 = new NavigatableList();
        list2.addItem(new ScrollableMenuItem("test2 1", () -> {System.out.println("test2 1 selected");}));
        list2.addItem(new ScrollableMenuItem("test2 2", () -> {System.out.println("test2 2 selected");}));
        menu2.setList(list2);


        ViewEngine engine = new ViewEngine();


        MainController controller = new MainController(engine);
        SwappableView view1 = new SwappableView();

        RowedCompositeContainer rows = new RowedCompositeContainer();
        rows.setHeight(400);
        rows.setWidth(400);
        rows.addDrawable(menu);
        rows.addDrawable(menu2);

        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(rows);
        view1.addDrawable(horizCenter);
        view1.addNavigatable(menu);
        view1.addNavigatable(menu2);
        controller.setMenuState(view1.getMenuViewContainer());


        //adpater
        SwingToDrawableAdapter adapter = new SwingToDrawableAdapter();
        ViewManager manager = new ViewManager();
        manager.addView(view1);
        adapter.setAdaptee(manager);
        engine.registerView(adapter);

        //engine.setVisible(true);
        engine.start();
    }
}
