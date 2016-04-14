package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Directionalizable;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class DirectionalViewObject extends LeafViewObject implements Directionalizable, Observer{
    private SimpleDynamicImage north;
    private SimpleDynamicImage south;
    private SimpleDynamicImage northeast;
    private SimpleDynamicImage northwest;
    private SimpleDynamicImage southeast;
    private SimpleDynamicImage southwest;

    private SimpleDynamicImage currentImage;

    private Directional subject;

    private DynamicImageDrawingStrategy drawingStrategy;

    @Override
    public void update() {
        subject.getDirection().setDirectionOf(this);
    }

    public <T extends Directional & ViewObservable> DirectionalViewObject(Position position,
                                                                          T subject,
                                                                          DynamicImageDrawingStrategy drawingStrategy,
                                                                          SimpleDynamicImage north,
                                                                          SimpleDynamicImage south,
                                                                          SimpleDynamicImage northeast,
                                                                          SimpleDynamicImage northwest,
                                                                          SimpleDynamicImage southeast,
                                                                          SimpleDynamicImage southwest) {
        super(position);
        this.drawingStrategy = drawingStrategy;
        this.subject = subject;
        this.north = north;
        this.south = south;
        this.northeast = northeast;
        this.northwest = northwest;
        this.southeast = southeast;
        this.southwest = southwest;

        //Attach to the observer
        subject.attach(this);
        update();
    }


    @Override
    public void draw(Graphics2D g) {
        drawingStrategy.draw(g, currentImage, getPosition());
    }

    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        fogOfWarViewObject.add(currentImage, getPosition(), drawingStrategy);
    }

    @Override
    public void setNorth() {
        currentImage = north;
    }

    @Override
    public void setSouth() {
        currentImage = south;
    }

    @Override
    public void setNorthEast() {
        currentImage = northeast;
    }

    @Override
    public void setSouthEast() {
        currentImage = southeast;
    }

    @Override
    public void setNorthWest() {
        currentImage = northwest;
    }

    @Override
    public void setSouthWest() {
        currentImage = southwest;
    }
}
