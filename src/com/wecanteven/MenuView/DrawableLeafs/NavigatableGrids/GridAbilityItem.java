package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 4/1/2016.
 */
public class GridAbilityItem extends SelectableItem{

    private String name;
    private int padding = 16;
    private Image icon = new ImageIcon("resources/Hands/Cyclops/hand.png").getImage();

    public GridAbilityItem(String name, String abilityName, SelectableMenuItemCommand command){
        this.setCommand(command);
        this.setName(name);
        icon = DynamicImageFactory.getInstance().loadDynamicImage("Items/Abilities/" + abilityName + ".xml").getImage();
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

        int imageSize = Math.min(windowHeight- 20 - padding,windowWidth - padding);

        g2d.drawImage(this.icon, x + windowWidth/2 - imageSize/2, y + imageSize/2 - 10,imageSize, imageSize, null);
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
