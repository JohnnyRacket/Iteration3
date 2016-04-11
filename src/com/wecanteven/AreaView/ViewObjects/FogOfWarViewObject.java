package com.wecanteven.AreaView.ViewObjects;

import com.wecanteven.AreaView.DynamicImages.DarkDynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;
import com.wecanteven.UtilityClasses.Tuple;
import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/9/2016.
 */
public class FogOfWarViewObject extends LeafViewObject {
    private ArrayList<CachedImage> cachedImages = new ArrayList<>();
    public FogOfWarViewObject(Position p) {
        super(p);
    }

    @Override
    public void draw(Graphics2D g) {
        cachedImages.forEach(
                (cachedImage) -> cachedImage.drawingStrategy.draw(g, cachedImage.image, cachedImage.position));
    }

    public void add(SimpleDynamicImage image, Position p, DynamicImageDrawingStrategy drawingStrategy) {

        cachedImages.add(new CachedImage(new DarkDynamicImage(image), p, drawingStrategy));
    }

    public Iterator<CachedImage> iterator() {
        return cachedImages.iterator();
    }

    public void append(FogOfWarViewObject viewObject) {
        viewObject.iterator().forEachRemaining( (tuple) ->  cachedImages.add(tuple) );
    }

    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        fogOfWarViewObject.append(this);
    }

    private class CachedImage {
        public DynamicImage image;
        public Position position;
        public DynamicImageDrawingStrategy drawingStrategy;

        public CachedImage(DynamicImage image, Position position, DynamicImageDrawingStrategy drawingStrategy) {
            this.image = image;
            this.position = position;
            this.drawingStrategy = drawingStrategy;
        }
    }
}
