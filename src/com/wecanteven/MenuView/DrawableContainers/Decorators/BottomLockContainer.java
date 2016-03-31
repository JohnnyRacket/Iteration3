package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class BottomLockContainer  extends DrawableDecorator {
    private int bottomOffset;

    public BottomLockContainer(Drawable d, int offsetFromEdge){
        super(d);
        this.bottomOffset = offsetFromEdge;
    }
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight){
        //logic here for right side
        int newY = windowHeight - this.getDrawable().getHeight() - bottomOffset;
        super.draw(g2d, x, newY, windowWidth, windowHeight);
    }

}
