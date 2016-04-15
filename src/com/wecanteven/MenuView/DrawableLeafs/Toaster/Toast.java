package com.wecanteven.MenuView.DrawableLeafs.Toaster;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class Toast extends Drawable {

    private String msg;
    private int duration;


    public Toast(int duration, String msg) {
        this.duration = duration;
        this.msg = msg;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        g2d.setFont(new Font("Helvetica",1,20));

        FontMetrics metrics = g2d.getFontMetrics();
        setWidth(metrics.stringWidth(getMsg()) + 50);

        g2d.setColor(Config.TRANSMEDIUMGREY);
        g2d.fillRect(x + (windowWidth/2 - getWidth()/2), windowHeight - windowHeight/4, (getWidth()),50);

        g2d.setColor(Color.white);
        g2d.drawString(getMsg(), x + (windowWidth/2 - metrics.stringWidth(msg)/2), (windowHeight - windowHeight/4) + (30));

    }

    public String getMsg() {
        return msg;
    }


    public int getDuration() {
        return duration;
    }
}
