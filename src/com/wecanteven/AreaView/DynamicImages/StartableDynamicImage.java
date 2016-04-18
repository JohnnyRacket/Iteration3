package com.wecanteven.AreaView.DynamicImages;

import com.wecanteven.AreaView.ViewTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 4/7/2016.
 */
public class StartableDynamicImage extends SimpleDynamicImage {
    private ArrayList<Image> images = new ArrayList<>();
    private String[] paths;
    private long duration;
    private long startTime = 0;

    public StartableDynamicImage(int xOffset, int yOffset, String ... paths) {
        super(xOffset, yOffset);
        this.paths = paths;
        for (int i = 0; i< paths.length; i++) {
            images.add(new ImageIcon(paths[i]).getImage());
        }
    }

    public void start(long duration) {
        this.duration = duration;
        startTime = ViewTime.getInstance().getCurrentTime();
    }

    @Override
    public Image getImage() {

        return images.get((int)((images.size() - 1)*getPercentage()));
    }

    @Override
    public String getImagePath() {
        return paths[(int)((images.size() - 1)*getPercentage())];
    }

    private double getPercentage() {
        if (startTime == 0) return 0;
        double percentage = (double)(ViewTime.getInstance().getCurrentTime() - startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }

    public void reset() {
        this.startTime = 0;
    }
}
