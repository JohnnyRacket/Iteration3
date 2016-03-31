package com.wecanteven.MenuView;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public abstract class Drawable{
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    public abstract void  draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight);
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int getWidth(){return  this.width;}
    public int getHeight(){return this.height;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
}
