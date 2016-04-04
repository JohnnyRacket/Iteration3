package com.wecanteven.Models.Stats;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.ViewEngine;

/**
 * Created by Brandon on 3/29/2016.
 */
public class test {
    public static void main(String ... args) {
        ViewEngine viewEngine = new ViewEngine();
        ModelEngine modelEngine = new ModelEngine();
        modelEngine.start();
        ModelTime modelTime = ModelTime.getInstance();

        LevelFactory levelFactory = new DopeAssLevelFactory();
        Map map = levelFactory.createMap();

//        Character character = new Character();
//        character.actionHandler = map;
//        map.add(character,new Location(5,0,1));
//        Avatar avatar = new Avatar(character);

       // MainController controller = new MainController(viewEngine,avatar);
        //controller.setPlayState();
       // modelTime.registerTickable(controller);
        //viewEngine.registerView(new AreaView());
        viewEngine.start();
    }
}
