package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class VerticalCenterContainer extends DrawableDecorator {

    public VerticalCenterContainer(Drawable d){
        super(d);
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int newY = windowHeight/2 - this.getDrawable().getHeight()/2 + y;
        super.draw(g2d,x,newY, windowWidth, windowHeight);
    }

}
