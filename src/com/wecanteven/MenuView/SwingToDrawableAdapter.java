package com.wecanteven.MenuView;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

/**
 * Created by John on 3/31/2016.
 */
public class SwingToDrawableAdapter extends JComponent{

    private ViewManager adaptee;

    @Override
    public void paintComponent(Graphics g){
        //redraw all the things
        int windowHeight = this.getRootPane().getHeight();
        int windowWidth = this.getRootPane().getWidth();

        Graphics2D g2d = (Graphics2D) g;

        adaptee.draw(g2d, windowWidth, windowHeight);
    }

    public ViewManager getAdaptee() {
        return adaptee;
    }

    public void setAdaptee(ViewManager adaptee) {
        this.adaptee = adaptee;
    }
}
