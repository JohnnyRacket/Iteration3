package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 4/8/2016.
 */
public class AnimatedCollapseDecorator extends DrawableDecorator {

    private int properHeight;
    private int properWidth;
    private int currentHeight = 0;

    private boolean opening;
    private boolean closing;

    public AnimatedCollapseDecorator(Drawable d) {
        super(d);
        properHeight = d.getHeight();
        properWidth = d.getWidth();
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        if(this.opening){
           openDraw(g2d,x,y,windowWidth,windowHeight);
        }else if(this.closing){
            closeDraw(g2d, x, y, windowWidth, windowHeight);
        }else {
            super.draw(g2d, x, y, windowWidth, windowHeight);
        }
    }

    private void openDraw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight){
        if(currentHeight < properHeight){
            this.getDrawable().setHeight(currentHeight);
            currentHeight +=properHeight/4;
        }else{
            setHeight(properHeight);
            opening = false;
        }
        super.draw(g2d, x, y, windowWidth, windowHeight);
    }

    private void closeDraw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight){
        if(currentHeight > 0){
            this.getDrawable().setHeight(currentHeight);
            currentHeight -=properHeight/4;
        }else{
            setHeight(0);
            closing = false;
        }
        super.draw(g2d, x, y, windowWidth, windowHeight);
    }

    @Override
    public void closeWindowAction() {
        this.closing = true;
        super.closeWindowAction();
    }

    @Override
    public void openWindowAction() {
        super.openWindowAction();
        this.opening = true;
    }


}
