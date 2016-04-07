package com.wecanteven.MenuView.DrawableContainers.Decorators;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 4/6/2016.
 */

public class TitleBarDecorator extends DrawableDecorator {

    private Color bgColor = Color.DARK_GRAY;
    private Color textColor = Color.WHITE;
    private String title;

    public TitleBarDecorator(Drawable d, String title) {
        super(d);
        this.title = title;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setColor(bgColor);
        g2d.fillRect(x,y-25,(windowWidth - x*2 ),50);
        g2d.setColor(textColor);
        g2d.setFont(new Font("Helvetica",1,20));
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(title,x + (windowWidth - x*2)/2 - metrics.stringWidth(title)/2,y+5);
        super.draw(g2d,x,y + 25, windowWidth, windowHeight);
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
}
