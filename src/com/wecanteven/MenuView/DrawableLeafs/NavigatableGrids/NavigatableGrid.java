package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.Navigatable;

import java.awt.*;

/**
 * Created by John on 4/1/2016.
 */
public class NavigatableGrid extends Drawable implements Navigatable {

    private NavigatableList list = new NavigatableList();

    public NavigatableGrid(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void select() {

    }
}
