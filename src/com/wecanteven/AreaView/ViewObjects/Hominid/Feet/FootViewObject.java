package com.wecanteven.AreaView.ViewObjects.Hominid.Feet;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by Alex on 4/2/2016.
 */
public class FootViewObject extends DirectionalViewObject {
    private Position reletivePosition = new Position(0,0,0);

    private double radius = 0;
    private double offset = 0;


    private DynamicImageDrawingStrategy drawingStrategy;
    public FootViewObject(Position position,
                          Direction direction,
                          DynamicImageDrawingStrategy drawingStrategy,
                          DynamicImage north,
                          DynamicImage south,
                          DynamicImage northeast,
                          DynamicImage northwest,
                          DynamicImage southeast,
                          DynamicImage southwest
    ) {
        super(position, direction, drawingStrategy, north, south, northeast, northwest, southeast, southwest);
        this.drawingStrategy = drawingStrategy;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        calculatePosition();
    }

    public void setOffset(double offset) {
        this.offset = offset;
        calculatePosition();
    }

    private void calculatePosition() {
        double angle = getDirection().getAngle();
        reletivePosition.setS(radius*Math.sin(angle) + offset*Math.sin(Math.PI/2 - angle));
        reletivePosition.setR(1/Math.cos(angle)*(radius*Math.cos(angle) + offset*Math.cos(Math.PI/2 - angle)));
    }


    @Override
    public void draw(Graphics2D g) {
        drawingStrategy.draw(g, getDynamicImage(), getPosition().add(reletivePosition));
    }

}
