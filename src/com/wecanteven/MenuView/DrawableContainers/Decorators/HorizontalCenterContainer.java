package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class HorizontalCenterContainer extends DrawableDecorator {

    public HorizontalCenterContainer(Drawable d){
        super(d);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int newX = windowWidth/2 - this.getDrawable().getWidth()/2 + x;
        super.draw(g2d,newX,y,windowWidth,windowHeight);
    }

}
