package com.wecanteven.MenuView.DrawableLeafs.ProgressBars;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by John on 4/8/2016.
 */
public class CircularHealthBar extends Drawable {

    private int percent = 70;
    private Color currentColor = Color.CYAN;
    private Color depletedColor = Color.DARK_GRAY;
    private Color borderColor = Color.DARK_GRAY;
    private int borderSize = 10;

    public CircularHealthBar(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
    }
    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height);
        float convertedPercent = percent*3.6f;

        //border
        g2d.setColor(borderColor);
        Arc2D border = new Arc2D.Double( x - borderSize/2, y - borderSize/2, radius + borderSize, radius + borderSize,0, 360, Arc2D.OPEN);
        g2d.fill(border);

        //background color
        g2d.setColor(depletedColor);
        Arc2D depletedArc = new Arc2D.Double( x, y, radius, radius,0, 360, Arc2D.OPEN);
        g2d.fill(depletedArc);
        //current color
        g2d.setColor(currentColor);
        Arc2D arc = new Arc2D.Double( x, y, radius, radius, (360f-convertedPercent)/2f + 90f, convertedPercent, Arc2D.OPEN);
        g2d.fill(arc);

    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
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

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }
}
