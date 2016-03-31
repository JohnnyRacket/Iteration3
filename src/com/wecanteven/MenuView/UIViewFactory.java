package com.wecanteven.MenuView;

import javax.swing.*;

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
        return null;
    }
}
