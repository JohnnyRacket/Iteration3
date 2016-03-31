package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public abstract class DrawableDecorator extends Drawable {
    private Drawable drawable;

    public DrawableDecorator(Drawable d){
            this.setDrawable(d);
        }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        this.getDrawable().draw(g2d,x,y,windowWidth,windowHeight);
    }

    @Override
    public int getX() {
        return this.getDrawable().getX();
    }

    @Override
    public int getY() {
        return this.getDrawable().getY();
    }

    @Override
    public int getWidth() {
        return this.getDrawable().getWidth();
    }

    @Override
    public int getHeight() {
        return this.getDrawable().getHeight();
    }

    @Override
    public void setWidth(int width) {
        this.getDrawable().setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.getDrawable().setHeight(height);
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
