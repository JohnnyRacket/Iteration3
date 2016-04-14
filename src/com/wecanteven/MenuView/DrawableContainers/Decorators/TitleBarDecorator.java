package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by John on 4/6/2016.
 */

public class TitleBarDecorator extends DrawableDecorator {

    private Color bgColor = Config.DARKGREY;
    private Color textColor = Color.WHITE;
    private String title;

    public TitleBarDecorator(Drawable d, String title) {
        super(d);
        this.title = title;
        this.setHeight(d.getHeight());
        //d.setHeight(d.getHeight()-50);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(Config.TEAL);
        g2d.fillRect(x,y,(this.getDrawable().getWidth()),50);
        g2d.setColor(textColor);
        g2d.setFont(new Font("Helvetica",1,20));
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(title,x + this.getDrawable().getWidth()/2 - metrics.stringWidth(title)/2,y+30);
        //this.getDrawable().setHeight(this.getHeight()-50);
        super.draw(g2d,x,y+50, windowWidth, windowHeight);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint shadow = new GradientPaint(0,y+50,new Color(.2f,.2f,.2f,.3f),0,y+58,new Color(.2f,.2f,.2f,.0f));
        //g2d.setColor(new Color(.1f,.1f,.1f,.3f));
        g2d.setPaint(shadow);
        g2d.fillRect(x,y+50,(this.getDrawable().getWidth()),8);

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
