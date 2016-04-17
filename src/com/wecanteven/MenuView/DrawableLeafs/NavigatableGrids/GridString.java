package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class GridString extends SelectableItem {

    private String name;
    private int padding = 16;

    public GridString(String name, SelectableMenuItemCommand command){
        this.setCommand(command);
        this.setName(name);
        //TODO use name to derive the img location, ill look at the view to see how this is done
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(Color.WHITE);

        int maxFont = 16;
        g2d.setFont(new Font("Helvetica", 1, maxFont));
        FontMetrics metrics = g2d.getFontMetrics();
        int i = 0;
        while(metrics.stringWidth(this.getName()) > windowWidth ){
            g2d.setFont(new Font("Helvetica", 1, maxFont - i));
            metrics = g2d.getFontMetrics();
            ++i;
        }
        g2d.drawString(this.name, x + windowWidth/2 - metrics.stringWidth(this.name)/2, y + windowHeight/2 + metrics.getHeight()/2);

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

}
