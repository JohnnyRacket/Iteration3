package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Directionalizable;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class DirectionalViewObject extends LeafViewObject implements Directionalizable{
    private DynamicImage north;
    private DynamicImage south;
    private DynamicImage northeast;
    private DynamicImage northwest;
    private DynamicImage southeast;
    private DynamicImage southwest;

    private DynamicImage currentImage;

    private Direction direction;

    private DynamicImageDrawingStrategy drawingStrategy;

    public DirectionalViewObject(Position position, Direction direction, DynamicImageDrawingStrategy drawingStrategy, DynamicImage north, DynamicImage south, DynamicImage northeast, DynamicImage northwest, DynamicImage southeast, DynamicImage southwest) {
        super(position);
        this.drawingStrategy = drawingStrategy;
        this.north = north;
        this.south = south;
        this.northeast = northeast;
        this.northwest = northwest;
        this.southeast = southeast;
        this.southwest = southwest;
        direction.setDirectionOf(this);
    }

    public DynamicImage getDynamicImage() {
        return currentImage;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void draw(Graphics2D g) {
        drawingStrategy.draw(g, currentImage, getPosition());
    }


    @Override
    public void setNorth() {
        direction = Direction.NORTH;
        currentImage = north;
    }

    @Override
    public void setSouth() {
        direction = Direction.SOUTH;
        currentImage = south;
    }

    @Override
    public void setNorthEast() {
        direction = Direction.NORTHEAST;
        currentImage = northeast;
    }

    @Override
    public void setSouthEast() {
        direction = Direction.SOUTHEAST;
        currentImage = southeast;
    }

    @Override
    public void setNorthWest() {
        direction = Direction.NORTHWEST;
        currentImage = northwest;
    }

    @Override
    public void setSouthWest() {
        direction = Direction.SOUTHWEST;
        currentImage = southwest;
    }
}
