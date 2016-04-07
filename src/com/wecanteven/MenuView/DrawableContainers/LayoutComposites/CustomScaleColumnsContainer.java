package com.wecanteven.MenuView.DrawableContainers.LayoutComposites;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 4/6/2016.
 */
public class CustomScaleColumnsContainer extends DrawableComposite {

    private int[] sizes;

    public CustomScaleColumnsContainer(int[] sizes){
        this.sizes = sizes;
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        Iterator<Drawable> iter = this.getDrawablesIterator();
        int totalSizes = 0;
        for(int i = 0; i < sizes.length; ++i){
            totalSizes += sizes[i];
        }
        for(int i = 0; i < sizes.length; ++i){
            Drawable current = iter.next();
            int thisColWidth = 0;
            if(current != null) {
                thisColWidth = (this.getWidth()*sizes[i])/totalSizes;
                current.setWidth(thisColWidth);
                current.setHeight(this.getHeight());
                current.draw(g2d, x, y, thisColWidth, this.getHeight());
            }
            x += thisColWidth; //starts new column one thing over
        }

    }
}
