package com.wecanteven.MenuView.DrawableLeafs;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.ViewEngine;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by John on 4/13/2016.
 */
public class BackgroundImageDrawable extends Drawable {

    private BufferedImage image;
    private ViewEngine viewEngine;

    private int xOld;
    private int yOld;

    public BackgroundImageDrawable(BufferedImage image, ViewEngine viewEngine){
        this.image = image;
        this.viewEngine = viewEngine;
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        if(xOld == windowWidth && yOld == windowHeight) {
            g2d.drawImage(image, x, y, null);
        }else{
            image = viewEngine.getScreenShot();
            xOld = windowWidth;
            yOld = windowHeight;
            g2d.drawImage(image, x, y, null);
        }
    }
}
