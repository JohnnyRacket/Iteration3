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
    public StartableDynamicImage(int xOffset, int yOffset, Image ... images) {
        super(xOffset, yOffset);
        this.images = images;
    }

    public void start(long duration) {
        this.duration = duration;
        startTime = ViewTime.getInstance().getCurrentTime();
    }

    @Override
    public Image getImage() {
        System.out.println(getPercentage());
        System.out.println((int)((images.length - 1)*getPercentage()));
        return images[(int)((images.length - 1)*getPercentage())];
    }

    private double getPercentage() {
        if (startTime == 0) return 0;
        double percentage = (double)(ViewTime.getInstance().getCurrentTime() - startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }
}
