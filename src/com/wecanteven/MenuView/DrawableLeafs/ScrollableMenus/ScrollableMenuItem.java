package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class ScrollableMenuItem extends Drawable implements SelectableItem{

    private SelectableMenuItemCommand command;
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

    @Override
    public void select() {
        try {
            command.execute();
        }catch (NullPointerException e){
            System.out.println(e);
        }
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

    public SelectableMenuItemCommand getCommand() {
        return command;
    }

    public void setCommand(SelectableMenuItemCommand command) {
        this.command = command;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(textColor);
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString(this.name, x + windowWidth/2 - metrics.stringWidth(this.name)/2, y + windowHeight/2 + metrics.getAscent()/2);

    }
}
