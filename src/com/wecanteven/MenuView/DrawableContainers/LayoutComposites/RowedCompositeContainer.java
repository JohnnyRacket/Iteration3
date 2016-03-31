package com.wecanteven.MenuView.DrawableContainers.LayoutComposites;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class RowedCompositeContainer extends DrawableComposite {
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        Iterator<Drawable> iter = this.getDrawablesIterator();
        int singleRowHeight = this.getHeight()/this.getSize();
        while(iter.hasNext()){
            Drawable current = iter.next();
            if(current != null) {
                current.setHeight(singleRowHeight);
                current.setWidth(this.getWidth());
                current.draw(g2d, x, y, this.getWidth(), singleRowHeight);
            }
            y += singleRowHeight; //starts new row one thing down

        }

    }
}
