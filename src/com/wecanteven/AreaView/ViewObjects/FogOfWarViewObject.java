package com.wecanteven.AreaView.ViewObjects;

import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;
import com.wecanteven.UtilityClasses.Tuple;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 4/9/2016.
 */
public class FogOfWarViewObject extends LeafViewObject {
    private ArrayList<Tuple<SimpleDynamicImage, Position>> cachedImages = new ArrayList<>();
    private DynamicImageDrawingStrategy drawingStrategy;
    public FogOfWarViewObject(Position p, DynamicImageDrawingStrategy drawingStrategy) {
        super(p);
        this.drawingStrategy = drawingStrategy;
    }

    @Override
    public void draw(Graphics2D g) {
        cachedImages.forEach( (tuple) -> drawingStrategy.draw(g, tuple.x, tuple.y));
    }

    public void add(SimpleDynamicImage image, Position p) {
        cachedImages.add(new Tuple<>(image, p));
    }
}
