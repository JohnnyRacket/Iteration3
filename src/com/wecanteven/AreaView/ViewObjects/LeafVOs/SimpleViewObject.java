package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class SimpleViewObject extends LeafViewObject {
    private SimpleDynamicImage dImage;
    private DynamicImageDrawingStrategy painter;

    public SimpleViewObject(Position position, SimpleDynamicImage dImage, DynamicImageDrawingStrategy painter) {
        super(position);
        this.dImage = dImage;
        this.painter = painter;
    }

    @Override
    public void draw(Graphics2D g) {
        painter.draw(g, dImage, getPosition());
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        parallelViewObject.add(dImage, getPosition(), painter);
    }
}
