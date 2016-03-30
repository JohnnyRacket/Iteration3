package com.wecanteven.AreaView.DynamicImages;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class ConstantDynamicImage extends DynamicImage {
    private Image image;

    public ConstantDynamicImage(int xOffset, int yOffset, Image image) {
        super(xOffset, yOffset);
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
