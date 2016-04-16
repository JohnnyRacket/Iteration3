package com.wecanteven;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.*;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.UtilityClasses.Config;

public class Main {

    public static void main(String[] args) {


        Thread.currentThread().setName("Main Thread (Controller)");
        ViewEngine engine = new ViewEngine();
        engine.setBackground(Config.BACKGROUNDBLUE);
        UIViewFactory factory = UIViewFactory.getInstance();
        MainController controller = new MainController(engine);


        ModelTime.getInstance().registerUnstoppable(controller);
        //engine.setVisible(true);
        ModelEngine mEngine = new ModelEngine();

        //gives the things to factory so it can inject them
        factory.setController(controller);
        factory.setmEngine(mEngine);
        factory.setvEngine(engine);

        factory.createMainMenuView();

        engine.start();
        mEngine.start();
    }
}
