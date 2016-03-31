package com.wecanteven;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
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

        NavigatableList list = new NavigatableList();
        list.addItem(new ScrollableMenuItem("test 1", null));
        list.addItem(new ScrollableMenuItem("test 2", null));
        menu.setList(list);


        ViewEngine engine = new ViewEngine();


        MainController controller = new MainController(engine);
        SwappableView view1 = new SwappableView();

        HorizontalCenterContainer horizCenter = new HorizontalCenterContainer(menu);
        view1.addDrawable(horizCenter);
        view1.addNavigatable(menu);
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
