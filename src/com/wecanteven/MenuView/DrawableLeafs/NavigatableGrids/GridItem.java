package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 4/1/2016.
 */
public class GridItem extends SelectableItem{

    private String name;
    private int padding = 16;
    private Image icon = new ImageIcon("resources/Hands/Cyclops/hand.png").getImage();

    public GridItem(String name, SelectableMenuItemCommand command){
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
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Helvetica", 1, 20));
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawImage(this.icon, x + padding/2, y + padding/2,windowWidth - padding, windowHeight- metrics.getAscent() - padding, null);
        g2d.drawString(this.name, x + windowWidth/2 - metrics.stringWidth(this.name)/2, y + windowHeight - padding/2);

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
