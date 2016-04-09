package com.wecanteven.AreaView.DynamicImages;

import java.awt.*;

/**
 * Created by alexs on 4/9/2016.
 */
public interface DynamicImage {
    Image getImage();
    String getImagePath();
    int getxOffset();
    int getyOffset();
}
