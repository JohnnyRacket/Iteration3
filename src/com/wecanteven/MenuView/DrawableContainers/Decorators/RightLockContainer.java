package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class RightLockContainer extends DrawableDecorator {
    private int rightOffset;

    public RightLockContainer(Drawable d, int offsetFromEdge){
        super(d);
        this.rightOffset = offsetFromEdge;
    }
    public void draw(Graphics2D g2d,int x, int y, int windowWidth, int windowHeight){
        //logic here for right side
        int newX = windowWidth - this.getDrawable().getWidth() - rightOffset;
        super.draw(g2d, newX, y, windowWidth, windowHeight);
    }

}
