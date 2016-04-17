package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class GridFace extends SelectableItem {

    private int padding = 16;

    private Image connery = new ImageIcon("resources/Face/Connery/menu.png").getImage();
    private Image testFace = new ImageIcon("resources/Face/TestFace/menu.png").getImage();

    private Image selectedFace;

    private String face;

    public GridFace(String face, SelectableMenuItemCommand command){
        this.setCommand(command);
        this.face = face;
        if(face.equals("Connery")) {
            this.selectedFace = connery;
        }else if(face.equals("Test Face")){
            this.selectedFace = testFace;
        }
        //TODO use name to derive the img location, ill look at the view to see how this is done
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
            g2d.setColor(Color.WHITE);

            int maxFont = 16;
            g2d.setFont(new Font("Helvetica", 1, maxFont));
            FontMetrics metrics = g2d.getFontMetrics();
            int i = 0;
            while(metrics.stringWidth(this.face) > windowWidth ){
                g2d.setFont(new Font("Helvetica", 1, maxFont - i));
                metrics = g2d.getFontMetrics();
                ++i;
            }

            int imageSize = Math.min(windowHeight- 20 - padding,windowWidth - padding);

            g2d.drawImage(this.selectedFace, x + windowWidth/2 - imageSize/2, y + imageSize/2 - 10,imageSize, imageSize, null);
            g2d.drawString(this.face, x + windowWidth/2 - metrics.stringWidth(this.face)/2, y + windowHeight - padding/2);

    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}
