package com.wecanteven.MenuView.DrawableContainers.LayoutComposites;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class ColumnatedCompositeContainer extends DrawableComposite {

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        Iterator<Drawable> iter = this.getDrawablesIterator();
        int singleColwidth = this.getWidth()/this.getSize();
        while(iter.hasNext()){
            Drawable current = iter.next();
            if(current != null) {
                current.setWidth(singleColwidth);
                current.setHeight(this.getHeight());
                current.draw(g2d, x, y, singleColwidth, this.getHeight());
            }
                x += singleColwidth; //starts new column one thing over
        }

    }
}
