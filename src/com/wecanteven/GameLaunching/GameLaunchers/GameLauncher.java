package com.wecanteven.GameLaunching.GameLaunchers;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.SaveLoad.Save.SaveGame;
import com.wecanteven.SaveLoad.Save.SaveToXMLFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
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

    public GameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine) {
        this.controller = controller;
        this.modelEngine = modelEngine;
        this.viewEngine = viewEngine;
    }

    public void launch(){
        //Nothing Happens
    }
    protected void createMap(){}

    protected void createAvatar(String occupation){}

    protected void populateMap(Map map) {}




    protected void initializeAreaView(){
        getViewEngine().clear();
        getViewEngine().getManager().popView();
        //viewEngine.registerView(new AreaView(avatar,map));
        getViewEngine().registerView(new AreaView(getAvatar(), getMap()));
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
        SaveToXMLFile.setAvatar(avatar);
        getController().setAvatar(getAvatar());
        UIViewFactory.getInstance().setAvatar(getAvatar());
        this.avatar = avatar;
    }

    protected MainController getController() {
        return controller;
    }

    protected void setController(MainController controller) {
        this.controller = controller;
    }

    protected ModelEngine getModelEngine() {
        return modelEngine;
    }

    protected void setModelEngine(ModelEngine modelEngine) {
        this.modelEngine = modelEngine;
    }

    protected ViewEngine getViewEngine() {
        return viewEngine;
    }

    protected void setViewEngine(ViewEngine viewEngine) {
        this.viewEngine = viewEngine;
    }
}
