package com.wecanteven.AreaView.DynamicImages;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class ConstantDynamicImage extends SimpleDynamicImage {
    private Image image;
    private String path;

    public ConstantDynamicImage(int xOffset, int yOffset, String path) {
        super(xOffset, yOffset);
        this.path = path;
        this.image = (new ImageIcon(path)).getImage();
    }

    @Override
    public String getImagePath() {
        return path;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
