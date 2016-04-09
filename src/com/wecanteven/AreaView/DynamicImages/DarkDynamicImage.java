package com.wecanteven.AreaView.DynamicImages;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 4/9/2016.
 */
public class DarkDynamicImage implements DynamicImage {

    private Image darkImage;
    private String path;
    private int xOffset;
    private int yOffset;

    public DarkDynamicImage(SimpleDynamicImage dynamicImage) {
        xOffset = dynamicImage.getxOffset();
        yOffset = dynamicImage.getyOffset();
        this.path = darkPath(dynamicImage.getImagePath());
        this.darkImage = (new ImageIcon(path)).getImage();
    }

    @Override
    public String getImagePath() {
        return path;
    }

    @Override
    public Image getImage() {
        return darkImage;
    }

    @Override
    public int getxOffset() {
        return xOffset;
    }

    @Override
    public int getyOffset() {
        return yOffset;
    }

    private String darkPath(String path) {
        return "resources/Dark" + path;
    }
}
