package com.wecanteven;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.UtilityClasses.Config;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello world");
        ViewEngine engine = new ViewEngine();
        engine.setBackground(Config.BACKGROUNDBLUE);
        UIViewFactory factory = UIViewFactory.getInstance();
        SwappableView view = factory.createMainMenuView();
        MainController controller = new MainController(engine);
        controller.setMenuState(view.getMenuViewContainer());


        //adapter
        SwingToDrawableAdapter adapter = new SwingToDrawableAdapter();
//        ViewManager composer = new ViewManager();
//        composer.addView(view);
//        adapter.setAdaptee(composer);

        //testing view manager here
        engine.getManager().addView(view);

        ModelTime.getInstance().registerTickable(controller);
        //engine.setVisible(true);
        ModelEngine mEngine = new ModelEngine();

        //gives the things to factory so it can inject them
        factory.setController(controller);
        factory.setmEngine(mEngine);
        factory.setvEngine(engine);

        engine.start();
        mEngine.start();
    }
}
