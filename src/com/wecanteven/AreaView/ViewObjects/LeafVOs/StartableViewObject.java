package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;

import java.awt.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class StartableViewObject extends LeafViewObject {
    private DynamicImageDrawingStrategy drawingStrategy;
    private StartableDynamicImage startableDynamicImage;

    public StartableViewObject(Position position, StartableDynamicImage startableDynamicImage, DynamicImageDrawingStrategy drawingStrategy) {
        super(position);
        this.drawingStrategy = drawingStrategy;
        this.startableDynamicImage = startableDynamicImage;
    }

    public void start(long duration) {
        startableDynamicImage.start(duration);
    }

    @Override
    public void draw(Graphics2D g) {
        drawingStrategy.draw(g, startableDynamicImage, getPosition());
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        parallelViewObject.add(startableDynamicImage, getPosition(), drawingStrategy);
    }
}
