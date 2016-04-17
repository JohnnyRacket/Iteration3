package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;
import com.wecanteven.UtilityClasses.GameColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class GridColor extends SelectableItem {

    private int padding = 16;
    private GameColor color;

    public GridColor(GameColor color, SelectableMenuItemCommand command){
        this.setCommand(command);
        this.color = color;
        //TODO use name to derive the img location, ill look at the view to see how this is done
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(color.dark);

        int maxFont = 16;
        g2d.setFont(new Font("Helvetica", 1, maxFont));
        FontMetrics metrics = g2d.getFontMetrics();
        int i = 0;
        while(metrics.stringWidth(color.race) > windowWidth ){
            g2d.setFont(new Font("Helvetica", 1, maxFont - i));
            metrics = g2d.getFontMetrics();
            ++i;
        }

        int imageSize = Math.min(windowHeight- 20 - padding,windowWidth - padding);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color.light);
        g2d.fillOval(x + windowWidth/2 - imageSize/2 + 12, y + imageSize/2 - 10, 50, 50);
        g2d.setColor(color.dark);
        g2d.drawString(color.race, x + windowWidth/2 - metrics.stringWidth(color.race)/2, y + windowHeight - padding/2);

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

}
