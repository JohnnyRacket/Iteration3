package com.wecanteven.MenuView.DrawableContainers;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class SwappableView {
    private ArrayList<Drawable> drawables = new ArrayList<>();

    public void addDrawable(Drawable drawable){
        drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable){
        drawables.remove(drawable);
    }

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight){
        Iterator<Drawable> iter = drawables.iterator();
        while(iter.hasNext()){
            Drawable current = iter.next();
            current.draw(g2d, current.getX(), current.getY(), windowWidth, windowHeight);
        }
    }

}
