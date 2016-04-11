package com.wecanteven.AreaView;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

import java.awt.*;

/**
 * Created by Alex on 4/11/2016.
 */
public class BackgroundDrawable {
    private DynamicImage background;
    private ViewObject centerTarget;
    private DynamicImageDrawingStrategy drawingStrategy;
    public BackgroundDrawable(DynamicImage image, DynamicImageDrawingStrategy drawingStrategy, ViewObject centerTarget) {
        this.background = image;
        this.drawingStrategy = drawingStrategy;
        this.centerTarget = centerTarget;
    }

    public void draw(Graphics2D g) {
        drawingStrategy.draw(g, background, getPosition());
    }

    private Position getPosition() {
        return centerTarget.getPosition().multiply(-0.1).add(new Position(-9,-1,0));
    }

}
