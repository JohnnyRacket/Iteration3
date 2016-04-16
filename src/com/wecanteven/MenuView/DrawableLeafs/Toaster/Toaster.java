package com.wecanteven.MenuView.DrawableLeafs.Toaster;

import com.wecanteven.MenuView.SwappableView;

import java.awt.*;
import java.util.*;

/**
 * Created by Joshua Kegley on 4/12/2016.
 */
public class Toaster {

    ArrayList<Toast> toasts = new ArrayList<Toast>();
    private int lastProcessed;

    public Toaster() {
        lastProcessed = (int) (System.currentTimeMillis() / 1000L);

    }

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight) {
        int currentTime = (int) (System.currentTimeMillis() / 1000L);
        if (!toasts.isEmpty()) {
            int timeSince = currentTime - lastProcessed;
            if(timeSince <= toasts.get(0).getDuration()){
                toasts.get(0).draw(g2d, 0, 0, windowWidth, windowHeight);
            }else {
                toasts.remove(0);
                lastProcessed = currentTime;
            }
        }else {
            lastProcessed = currentTime;
        }

    }

    public void addToast(Toast toast) {
        toasts.add(toast);
    }
}
