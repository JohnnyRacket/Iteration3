package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class BottomTitleBarDecorator extends DrawableDecorator {

    private Color bgColor = Config.DARKGREY;
    private Color textColor = Color.WHITE;
    private String title;

    public BottomTitleBarDecorator(Drawable d, String title) {
        super(d);
        this.title = title;
        this.setHeight(d.getHeight());
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(getBgColor());
        g2d.fillRect(x, y,(this.getDrawable().getWidth()),50);
        g2d.setColor(getTextColor());
        g2d.setFont(new Font("Helvetica",1,20));
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(getTitle(),x + this.getDrawable().getWidth()/2 - metrics.stringWidth(getTitle())/2,y+30);
        //this.getDrawable().setHeight(this.getHeight()-50);
        super.draw(g2d,x,y+50, windowWidth, windowHeight);
    }

    @Override
    public void setHeight(int height) {
        //System.out.println
        super.setHeight(height - 50);
    }
    @Override
    public int getHeight(){
        return super.getHeight()+50;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

