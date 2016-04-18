package com.wecanteven.MenuView.DrawableLeafs.Toaster;

import com.wecanteven.MenuView.SwappableView;

import java.awt.*;
import java.util.*;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class Toaster {

    Toast toast = null;
    private int lastProcessed;

    public Toaster() {
        lastProcessed = (int) (System.currentTimeMillis() / 1000L);

    }

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight) {
        int currentTime = (int) (System.currentTimeMillis() / 1000L);
        if (toast != null) {
            int timeSince = currentTime - lastProcessed;
            if(timeSince <= toast.getDuration()){
                toast.draw(g2d, 0, 0, windowWidth, windowHeight);
            }else {
                toast = null;
                lastProcessed = currentTime;
            }
        }else {
            lastProcessed = currentTime;
        }

    }

    public void addToast(Toast toast) {
        this.toast = toast;
    }
}
