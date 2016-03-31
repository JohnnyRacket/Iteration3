package com.wecanteven.MenuView.DrawableContainers.LayoutComposites;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public abstract class DrawableComposite extends Drawable{
    private ArrayList<Drawable> drawables = new ArrayList();
    private boolean scaling = true;


    public DrawableComposite(Drawable... drawables){
            for(Drawable drawable : drawables){
                this.drawables.add(drawable);
            }
    }
    @Override
    public abstract void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight);


    public void addDrawable(Drawable d){
        drawables.add(d);
    }
    public void removeDrawable(Drawable d){
        drawables.remove(d);
    }

    public Iterator<Drawable> getDrawablesIterator(){
        return this.drawables.iterator();
    }

    public int getSize(){
        return drawables.size();
    }

    public boolean isScaling() {
        return scaling;
    }

    public void setScaling(boolean scaling) {
        this.scaling = scaling;
    }
}
