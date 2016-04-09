package com.wecanteven.AreaView.ViewObjects.DrawingStategies;


import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public interface DynamicImageDrawingStrategy {
    void draw(Graphics2D g, SimpleDynamicImage dImage, Position position);
}
