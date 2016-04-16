package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;
import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableContainers.Decorators.HorizontalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.Decorators.TitleBarDecorator;
import com.wecanteven.MenuView.DrawableContainers.Decorators.VerticalCenterContainer;
import com.wecanteven.MenuView.DrawableContainers.LayoutComposites.ColumnatedCompositeContainer;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.NavigatableList;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.SwappableView;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by Joshua Kegley on 4/14/2016.
 */
public class NavigatableGridWithCenterTitle extends NavigatableGrid {

    private Color inactiveColor;
    private String title;
    TitleBarDecorator titleBar;

    public NavigatableGridWithCenterTitle(int width, int height, int cols, int rows, Color bgColor, Color inactiveColor, Color titleColor, String title) {
        super(width, height, cols, rows);
        setBgColor(bgColor);
        this.inactiveColor = inactiveColor;
        this.title = title;
        titleBar = new TitleBarDecorator(this, getTitle());
        titleBar.setBgColor(titleColor);
    }

    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int index = 0;
        int offsetX = x;
        int offsetY = y;
        int calculatedPadding = 10;
        int itemWidth = (this.getWidth() - calculatedPadding)/getCols();
        int itemHeight = (this.getHeight() - calculatedPadding)/getRows();

        g2d.setColor(isActive() ? getBgColor() : getInActiveBgColor());
        g2d.fillRect(offsetX, offsetY, this.getWidth(), this.getHeight());

        //paint list items
        NavigatableList tmpList = getList().clone();
        Iterator<SelectableItem> iter = tmpList.getIterator();
        while (iter.hasNext()) {
            if(offsetY-y+itemHeight > this.getHeight()){
                return;
            }
            if (index == getList().getCurrentIndex() && isActive()) {
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

            if(index % getCols() == 0){
                offsetY += itemHeight;
                offsetX = x;
            }else{
                offsetX += itemWidth;
            }
        }
    }

    public void setInactiveColor(Color color) {
        this.inactiveColor = color;
    }

    public Color getInActiveBgColor() {
        return inactiveColor;
    }

    public Color getInactiveColor() {
        return inactiveColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        titleBar.setTitle(title);
    }

    public TitleBarDecorator getTitleBar() {
        return titleBar;
    }
}
