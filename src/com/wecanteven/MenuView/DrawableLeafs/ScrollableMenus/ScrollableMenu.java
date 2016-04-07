package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.Navigatable;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 3/31/2016.
 */
public class ScrollableMenu extends Drawable implements Navigatable {

    private int itemHeight;
    private NavigatableList list;
    private Color selectedColor;
    private Color bgColor;
    private int bgHeight = 0;
    private int bgWidth = 0;
    private int xOffset = 0;
    private int yOffset = 0;
    private int padding; //this will be represented in %
    private boolean active = false;
    private boolean scalingX = false;
    private boolean scalingY = false;

    public ScrollableMenu(){
        //this.setBackground(new Color(0,0,0,0));
        this.setSelectedColor(new Color(255,255,255,80));
        this.setBgColor(new Color (150,150,150));
        this.setPadding(10);
        this.setItemHeight(30);
    }
    public ScrollableMenu(int bgWidth, int bgHeight){
        this();
        this.setWidth(bgWidth);
        this.setHeight(bgHeight);
    }
    public ScrollableMenu(int bgWidth, int bgHeight, int itemHeight){
        this(bgWidth, bgHeight);
        this.setItemHeight(itemHeight);
    }

    public ScrollableMenu(int bgWidth, int bgHeight, int itemHeight, NavigatableList list){
        this( bgWidth,  bgHeight,  itemHeight);
        this.setList(list);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int offsetY = y;
        int offsetX = x;
        int index = 0;
        int calculatedPadding = (int)(((float)this.padding/100f)* (float)this.getWidth());


        //paint bg
        if(this.getHeight() != -1 && this.getWidth() != -1 ) {
            g2d.setColor(bgColor);
            g2d.fillRect(offsetX, offsetY, this.getWidth(), this.getHeight());
        }

        //paint list items
        NavigatableList tmpList = list.clone();
        Iterator<SelectableItem> iter = tmpList.getIterator();
        while (iter.hasNext()) {
            if (index == list.getCurrentIndex() && active) {
                //System.out.println(list.getCurrentIndex());
                SelectableItem current = iter.next();
                g2d.setColor(selectedColor);
                g2d.fillRect(offsetX + calculatedPadding / 2, offsetY + calculatedPadding/2, this.getWidth() - calculatedPadding, itemHeight);
                current.draw(g2d, offsetX + calculatedPadding / 2 , offsetY + calculatedPadding/2, this.getWidth() - calculatedPadding, itemHeight);
            } else {
                SelectableItem current = iter.next();
                current.draw(g2d, offsetX + calculatedPadding / 2, offsetY + calculatedPadding/2, this.getWidth() - calculatedPadding, itemHeight);
            }
            offsetY += itemHeight;
            index++;
        }
    }

    @Override
    public int getX() {
        return xOffset;
    }

    @Override
    public int getY() {
        return yOffset;
    }

    @Override
    public int getWidth() {
        return bgWidth;
    }

    @Override
    public int getHeight() {
        return bgHeight;
    }

    @Override
    public void setWidth(int width){
        this.bgWidth = width;
    }

    @Override
    public void setHeight(int height){
        this.bgHeight = height;
    }

    // Public Api
    public void setList(NavigatableList list){
        this.list = list;
    }

    public void setItemHeight(int height){
        this.itemHeight = height;
    }

    @Override
    public void down(){
        //move list down
        this.list.setCurrentIndex(this.list.getCurrentIndex() + 1);
    }
    @Override
    public void up(){
        //move visual up
        this.list.setCurrentIndex(this.list.getCurrentIndex() - 1);
    }
    @Override
    public void select( ){
        list.select();
    }
    @Override
    public void left( ){
        //nothing
    }
    @Override
    public void right( ){
        //nothing
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int percentPadding) {
        if(percentPadding > 100){
            this.padding = 100;
        }else if(percentPadding < 0){
            this.padding = 0;
        }else {
            this.padding = percentPadding;
        }
    }

    public void addItem (ScrollableMenuItem item){
        this.list.addItem(item);
    }

    public void removeItem (ScrollableMenuItem item){
        this.list.removeItem(item);
    }
    public SelectableItem removeItem (int index){
        return this.list.removeItemFromIndex(index);
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }



    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    @Override
    public void active(boolean active) {
        this.active = active;
    }
}
