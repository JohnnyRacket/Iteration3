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
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.MenuView.ViewManager;
import com.wecanteven.Models.ModelTime.ModelTime;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello world");
        ViewEngine engine = new ViewEngine();

        UIViewFactory factory = new UIViewFactory(engine);

        SwappableView view = factory.createMainMenuView();

        MainController controller = new MainController(engine);
        factory.setController(controller);
        controller.setMenuState(view.getMenuViewContainer());


        //adpater
        SwingToDrawableAdapter adapter = new SwingToDrawableAdapter();
        ViewManager manager = new ViewManager();
        manager.addView(view);
        adapter.setAdaptee(manager);
        engine.registerView(adapter);

        ModelTime.getInstance().registerTickable(controller);
        //engine.setVisible(true);
        ModelEngine mEngine = new ModelEngine();
        engine.start();
        mEngine.start();
    }
}
