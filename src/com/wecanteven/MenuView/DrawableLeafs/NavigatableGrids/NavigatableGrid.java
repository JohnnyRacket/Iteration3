package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.ScrollableMenuItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.Navigatable;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 4/1/2016.
 */
public class NavigatableGrid extends Drawable implements Navigatable {

    private NavigatableList list = new NavigatableList();
    private int cols;
    private int rows;
    private Color bgColor;
    private boolean active = false;

    public NavigatableGrid(int width, int height, int cols, int rows){
        this.setWidth(width);
        this.setHeight(height);
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int index = 0;
        int offsetX = x;
        int offsetY = y;
        int calculatedPadding = 10;
        int itemWidth = (this.getWidth() - calculatedPadding)/cols;
        int itemHeight = (this.getHeight() - calculatedPadding)/rows;

        g2d.setColor(bgColor);
        g2d.fillRect(offsetX, offsetY, this.getWidth(), this.getHeight());

        //paint list items
        Iterator<SelectableItem> iter = list.getIterator();
        while (iter.hasNext()) {
            if (index == list.getCurrentIndex() && active) {
                //System.out.println(list.getCurrentIndex());
                SelectableItem current = iter.next();
                g2d.setColor(new Color(1f,1f,1f,.4f));
                g2d.fillRect(offsetX + calculatedPadding / 2, offsetY + calculatedPadding/2, itemWidth, itemHeight);
                current.draw(g2d, offsetX + calculatedPadding / 2 , offsetY + calculatedPadding/2, itemWidth, itemHeight);
            } else {
                SelectableItem current = iter.next();
                current.draw(g2d, offsetX + calculatedPadding / 2, offsetY + calculatedPadding/2, itemWidth, itemHeight);
            }

            index++;

            if(index % cols == 0){
                offsetY += itemHeight;
                offsetX = x;
            }else{
                offsetX += itemWidth;
            }


        }
    }

    @Override
    public void up() {
        this.list.setCurrentIndex(this.list.getCurrentIndex() - cols);
    }

    @Override
    public void down() {
        this.list.setCurrentIndex(this.list.getCurrentIndex() + cols);
    }

    @Override
    public void left() {
        this.list.setCurrentIndex(this.list.getCurrentIndex() - 1);
    }

    @Override
    public void right() {
        this.list.setCurrentIndex(this.list.getCurrentIndex() + 1);
    }

    @Override
    public void select() {
        list.select();
    }

    public NavigatableList getList() {
        return list;
    }

    public void setList(NavigatableList list) {
        this.list = list;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public void active(boolean active) {
        this.active = active;
    }
}
