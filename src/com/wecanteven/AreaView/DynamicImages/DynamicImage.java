package com.wecanteven.AreaView.DynamicImages;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public abstract class DynamicImage {
    private int xOffset;
    private int yOffset;

    public final int getxOffset() {
        return xOffset;
    }

    public final int getyOffset() {
        return yOffset;
    }

    public DynamicImage(int xOffset, int yOffset) {

        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public abstract Image getImage();
}
