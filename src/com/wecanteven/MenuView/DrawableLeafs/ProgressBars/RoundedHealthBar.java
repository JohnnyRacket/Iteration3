package com.wecanteven.MenuView.DrawableLeafs.ProgressBars;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by John on 4/16/2016.
 */
public class RoundedHealthBar extends Drawable {

    private Color currentColor = Color.green;
    private Color depletedColor = Color.RED;
    private int percent;
    int roundness = 15;

    public RoundedHealthBar(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
        this.setRoundness(getHeight()/2);
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

        int width = this.getWidth();
        int height = this.getHeight();
        int xOffset = x;
        int yOffset = y;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        RoundRectangle2D rect = new RoundRectangle2D.Float(x,y,width,height,roundness,roundness);
        g2d.setColor(depletedColor);
        g2d.fill(rect);

        rect = new RoundRectangle2D.Float(x,y,(float)width*((float)percent/100f),height,roundness,roundness);
        g2d.setColor(currentColor);
        g2d.fill(rect);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Color getDepletedColor() {
        return depletedColor;
    }

    public void setDepletedColor(Color depletedColor) {
        this.depletedColor = depletedColor;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        if(percent > 100 || percent < 0)throw new IllegalArgumentException("Value out of range (0-100)");
        this.percent = percent;

    }

    public int getRoundness() {
        return roundness;
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
    }
}
