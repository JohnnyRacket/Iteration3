package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import java.awt.*;

/**
 * Created by John on 4/7/2016.
 */
public class LeftAlignScrollableMenuItem extends ScrollableMenuItem {
    public LeftAlignScrollableMenuItem(String name, SelectableMenuItemCommand command) {
        super(name, command);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(this.getTextColor());
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString(this.getName(), x , y + windowHeight/2 + metrics.getAscent()/2);
    }
}
