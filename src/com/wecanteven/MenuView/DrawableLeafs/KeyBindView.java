package com.wecanteven.MenuView.DrawableLeafs;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by John on 4/11/2016.
 */
public class KeyBindView extends Drawable {

    public KeyBindView(int height, int width){
        this.setHeight(height);
        this.setWidth(width);
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setFont(new Font("Helvetica", 1, 20));
        g2d.setColor(Config.MEDIUMGREY);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.fillRect(x,y,this.getWidth(),this.getHeight());
        g2d.setColor(Color.white);
        g2d.drawString("Press key to bind...",x + this.getWidth()/2 - metrics.stringWidth("Press key to bind...")/2,y + this.getHeight()/2 + metrics.getAscent()/2);
    }
}
