package com.wecanteven.MenuView.DrawableLeafs.ProgressBars;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by John on 4/13/2016.
 */
public class HexagonProgressBar extends Drawable {

    private Polygon hex = new Polygon();
    private float width;
    private float height;
    private Point2D top, bot, left, right;
    private int borderSize = 4;

    private Color currentColor;
    private Color depletedColor;
    private Color borderColor;

    public HexagonProgressBar(float botWidth, float height){
        this.width = botWidth;
        this.height = height;
        top = new Point2D.Float(0,0);
        bot = new Point2D.Float(width *2,0);
        left = new Point2D.Float(width/2,height);
        right = new Point2D.Float(width/2 + width,height);


    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        hex.reset();
        hex.addPoint((int)top.getX() + x,(int)top.getY() + y);
        hex.addPoint((int)bot.getX() + x,(int)bot.getY() + y);
        hex.addPoint((int)right.getX() + x,(int)right.getY() + y);
        hex.addPoint((int)left.getX() + x,(int)left.getY() + y);

        g2d.setColor(borderColor);
        g2d.fillPolygon(hex);



        hex.reset();
        hex.addPoint((int)top.getX() + x + borderSize*3,(int)top.getY() + y + borderSize);
        hex.addPoint((int)bot.getX() + x - borderSize*3,(int)bot.getY() + y + borderSize);
        hex.addPoint((int)right.getX() + x - borderSize,(int)right.getY() + y - borderSize);
        hex.addPoint((int)left.getX() + x + borderSize,(int)left.getY() + y - borderSize);

        g2d.setColor(currentColor);
        g2d.fillPolygon(hex);



    }



    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getDepletedColor() {
        return depletedColor;
    }

    public void setDepletedColor(Color depletedColor) {
        this.depletedColor = depletedColor;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }
}
