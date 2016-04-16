package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by John on 4/16/2016.
 */
public class HUDview extends Drawable {
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

        int xOffset = x + 20;
        int yOffset = y + 20;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Color testYellow = new Color(114, 135, 184);
        //make it 200 heigh
        g2d.setColor(testYellow);
        g2d.fillPolygon(new int[]{20,20, 120,220,220,120},new int[]{ 190, 70,20,70,190,230},6);

        g2d.fillPolygon(new int[]{220,220, 400,440},new int[]{ 110, 70,70,110},4);
        g2d.setColor(new Color(42, 230, 16));
        g2d.fillPolygon(new int[]{230,230, 390,410},new int[]{ 100, 80,80,100},4);

        g2d.setColor(testYellow);
        RoundRectangle2D rect = new RoundRectangle2D.Float(220,110,200,40,15,15);
        g2d.fillPolygon(new int[]{220,220, 440,440},new int[]{ 150, 110,110,150},4);
        g2d.setColor(new Color(230, 67, 205));
        g2d.fill(rect);
        //g2d.fillPolygon(new int[]{230,230, 430,430},new int[]{ 140, 120,120,140},4);

        g2d.setColor(testYellow);
        g2d.fillPolygon(new int[]{220,220, 440,400},new int[]{ 190, 150,150,190},4);
        g2d.setColor(new Color(51, 84, 230));
        g2d.fillPolygon(new int[]{230,230, 410,390},new int[]{ 180, 160,160,180},4);
    }
}
