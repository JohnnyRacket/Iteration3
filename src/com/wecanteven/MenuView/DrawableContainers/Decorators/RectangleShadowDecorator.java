package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 4/13/2016.
 */
public class RectangleShadowDecorator extends DrawableDecorator {

    private int shadowSize = 8;
    public RectangleShadowDecorator(Drawable d) {
        super(d);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int width = this.getWidth();
        int height = this.getHeight();

        //bottom shadow
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint shadow = new GradientPaint(0,y+height,new Color(.2f,.2f,.2f,.3f),0,y+height+shadowSize,new Color(.2f,.2f,.2f,.0f));
        g2d.setPaint(shadow);
        g2d.fillRect(x,y+height,width,shadowSize);

        //top shadow
        shadow = new GradientPaint(0,y-shadowSize,new Color(.2f,.2f,.2f,.0f),0,y,new Color(.2f,.2f,.2f,.3f));
        g2d.setPaint(shadow);
        g2d.fillRect(x,y-shadowSize,width,shadowSize);

        //left shadow
        shadow = new GradientPaint(x-shadowSize,0,new Color(.2f,.2f,.2f,.0f),x,0,new Color(.2f,.2f,.2f,.3f));
        g2d.setPaint(shadow);
        g2d.fillRect(x-shadowSize,y,shadowSize,height);

        //right shadow
        shadow = new GradientPaint(x+width,0,new Color(.2f,.2f,.2f,.3f),x+width+shadowSize,0,new Color(.2f,.2f,.2f,0f));
        g2d.setPaint(shadow);
        g2d.fillRect(x+width,y,shadowSize,height);

        //topleft corner
        shadow = new GradientPaint(x-shadowSize,y-shadowSize,new Color(.2f,.2f,.2f,.0f),x,y,new Color(.2f,.2f,.2f,.3f));
        g2d.setPaint(shadow);
        g2d.fillRect(x-shadowSize,y-shadowSize,shadowSize*4,shadowSize*4);

        super.draw(g2d, x, y, windowWidth, windowHeight);
    }
}
