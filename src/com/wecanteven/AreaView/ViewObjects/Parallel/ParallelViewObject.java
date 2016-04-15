package com.wecanteven.AreaView.ViewObjects.Parallel;

import com.wecanteven.AreaView.DynamicImages.DarkDynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 4/9/2016.
 */
public abstract class ParallelViewObject extends LeafViewObject {
    private ArrayList<CachedImage> cachedImages = new ArrayList<>();
    public ParallelViewObject(Position p) {
        super(p);
    }

    @Override
    public void draw(Graphics2D g) {
        cachedImages.forEach(
                (cachedImage) -> cachedImage.drawingStrategy.draw(g, cachedImage.image, cachedImage.position));
    }

    public void add(SimpleDynamicImage image, Position p, DynamicImageDrawingStrategy drawingStrategy) {

        cachedImages.add(new CachedImage(convert(image), p, drawingStrategy));
    }

    protected abstract DynamicImage convert(SimpleDynamicImage image);

    public Iterator<CachedImage> iterator() {
        return cachedImages.iterator();
    }

    public void append(ParallelViewObject viewObject) {
        viewObject.iterator().forEachRemaining( (tuple) ->  cachedImages.add(tuple) );
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        parallelViewObject.append(this);
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
