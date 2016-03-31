package com.wecanteven.MenuView.ScrollableMenus;

import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class ScrollableMenuItem {

    private String name;
    private ScrollableMenuItemCommand command;
    private Color textColor;

    public ScrollableMenuItem(String name, ScrollableMenuItemCommand command){
        this.setName(name);
        this.setCommand(command);
        this.setTextColor(Color.BLACK);
    }
    public ScrollableMenuItem(String name, ScrollableMenuItemCommand command, Color textColor){
        this(name, command);
        this.setTextColor(textColor);
    }

    public void select() {
        try {
            command.execute();
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics2D g2d, int xPos, int yPos, int width, int height) {
        g2d.setColor(textColor);
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString(this.name, xPos + width/2 - metrics.stringWidth(this.name)/2, yPos + height/2 + metrics.getAscent()/2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScrollableMenuItemCommand getCommand() {
        return command;
    }

    public void setCommand(ScrollableMenuItemCommand command) {
        this.command = command;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
