package com.wecanteven.AreaView.DynamicImages;

import com.wecanteven.AreaView.ViewTime;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 4/7/2016.
 */
public class StartableDynamicImage extends DynamicImage {
    private Image[] images;
    private long duration;
    private long startTime = 0;
    public StartableDynamicImage(int xOffset, int yOffset, long duration, Image ... images) {
        super(xOffset, yOffset);
        this.images = images;
        this.duration = duration;
    }

    public void start() {
        startTime = ViewTime.getInstance().getCurrentTime();
    }

    @Override
    public Image getImage() {
        return images[(int)(images.length*getPercentage())];
    }

    private double getPercentage() {
        if (startTime == 0) return 0;
        double percentage = (ViewTime.getInstance().getCurrentTime() - startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }
}
