package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class ScrollableMenuItem extends SelectableItem{

    private String name;
    private Color textColor;

    public ScrollableMenuItem(String name, SelectableMenuItemCommand command){
        this.setName(name);
        this.setCommand(command);
        this.setTextColor(Color.BLACK);
    }
    public ScrollableMenuItem(String name, SelectableMenuItemCommand command, Color textColor){
        this(name, command);
        this.setTextColor(textColor);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(textColor);
        int maxFont = 16;
        g2d.setFont(new Font("Helvetica", 1, maxFont));
        FontMetrics metrics = g2d.getFontMetrics();
        int i = 0;
        while(metrics.getHeight() + 8 > windowWidth ){
            g2d.setFont(new Font("Helvetica", 1, maxFont - i));
            metrics = g2d.getFontMetrics();
            ++i;
        }
        g2d.drawString(this.name, x + windowWidth/2 - metrics.stringWidth(this.name)/2, y + windowHeight/2 + metrics.getAscent()/2);

    }
}
