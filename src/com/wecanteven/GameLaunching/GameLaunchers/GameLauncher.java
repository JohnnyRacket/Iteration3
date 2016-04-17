package com.wecanteven.GameLaunching.GameLaunchers;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
import com.wecanteven.ViewEngine;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public abstract class GameLauncher {

    private Map map;
    private Avatar avatar;
    private MainController controller;
    private ModelEngine modelEngine;
    private ViewEngine viewEngine;
    private LevelFactory levelFactory;

    public GameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine) {
        this.controller = controller;
        this.modelEngine = modelEngine;
        this.viewEngine = viewEngine;
    }

    abstract void launch();
    abstract void createMap();

    abstract void createAvatar(String occupation);

    abstract void populateMap(Map map);
    public LevelFactory getLevelFactory() {return levelFactory; }
    public void setLevelFactory(LevelFactory levelFactory){
        this.levelFactory = levelFactory;
    }



    protected void initializeAreaView(){
        getViewEngine().clear();
        getViewEngine().getManager().popView();



        getViewEngine().registerView(new AreaView(getMap(), getLevelFactory() ));
        getController().setPlayState();
    }

    protected void initializeUIView(){
        UIViewFactory.getInstance().createHUDView(getAvatar().getCharacter());
    }


    /* GETTERS AND SETTERS BELOW */


    protected Map getMap() {
        return map;
    }

    protected void setMap(Map map) {
        SaveToXMLFile.setMap(map);
        this.map = map;
    }

    protected Avatar getAvatar() {
        return avatar;
    }

    protected void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        SaveToXMLFile.setAvatar(avatar);
        getController().setAvatar(getAvatar());
        UIViewFactory.getInstance().setAvatar(getAvatar());

    }

    protected MainController getController() {
        return controller;
    }

    protected void setController(MainController controller) {
        this.controller = controller;
    }


    protected ViewEngine getViewEngine() {
        return viewEngine;
    }

}
