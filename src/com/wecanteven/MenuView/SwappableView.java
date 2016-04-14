package com.wecanteven.MenuView;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableContainers.MenuViewContainer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class SwappableView {
    private ArrayList<Drawable> drawables = new ArrayList<>();
    private MenuViewContainer menus = new MenuViewContainer();

    public void addDrawable(Drawable drawable){
        drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable){
        drawables.remove(drawable);
    }

    public void addNavigatable(Navigatable navigatable){menus.add(navigatable);}

    public void removeNavigatable(Navigatable navigatable){menus.remove(navigatable);}

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight){
        //System.out.println("drawing drawables");
        Iterator<Drawable> iter = drawables.iterator();
        while(iter.hasNext()){
            Drawable current = iter.next();
            current.draw(g2d, current.getX(), current.getY(), windowWidth, windowHeight);
        }
    }

    public MenuViewContainer getMenuViewContainer(){
        return this.menus;
    }

    public void closeDrawables(){
        Iterator<Drawable> iter = drawables.iterator();
        while(iter.hasNext()){
            Drawable current = iter.next();
            current.closeWindowAction();
        }
    }

}
