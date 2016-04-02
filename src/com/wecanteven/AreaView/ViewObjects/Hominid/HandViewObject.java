package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.DynamicImages.ConstantDynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;
import java.util.Observable;

public class HandViewObject extends LeafViewObject {
    private double radius;
    private double angle;
    private double height;
    private double offset;
    private double rOffset;
    private double sOffset;
    private double zOffset;
    private final double CAMERA_TILT_FACTOR = Math.cos(Math.PI/4)*Math.sin(Math.toRadians(34));
    private DynamicImage handsImage = DynamicImageFactory.getInstance().loadDynamicImage("Hands/Human/hand.xml");

    private DynamicImageDrawingStrategy drawingStrategy;


    public HandViewObject(Position position, double radius, double height, double angle, double offset, DynamicImageDrawingStrategy drawingStrategy) {
        super(position);
        this.radius = radius;
        this.height = height;
        this.angle = angle;
        this.offset = offset;
        this.drawingStrategy = drawingStrategy;
    }

    public void draw(Graphics2D graphic) {
        calculatePixelOffsets();
//        Position handPosition = getPosition();
//        handPosition.setR(handPosition.getR() + xOffset);
//        handPosition.setS(handPosition.getS() + yOffset);
        drawingStrategy.draw(graphic, handsImage, getOffsetPosition());
    }

    private Position getOffsetPosition() {
        Position offSetPosition = new Position(getPosition().getR(), getPosition().getS(), getPosition().getZ());
        offSetPosition.setS(offSetPosition.getS() + rOffset);
        offSetPosition.setR(offSetPosition.getR() + sOffset);

       offSetPosition.setZ(offSetPosition.getZ() + zOffset);
        return offSetPosition;
    }

    public void calculatePixelOffsets() {
        rOffset = (1/Math.cos(Math.PI/6))*((radius*Math.cos(angle) + offset*Math.cos(Math.PI/2- angle)));
        sOffset = ((radius*Math.sin(angle) + offset*Math.sin(angle + Math.PI/2)) );
        zOffset = height;

        //zOffset = (int)(radius*Math.tan(angle) + offset*Math.tan(angle + Math.PI/2));

//        System.out.println("xPixelOffset: " + xPixelOffset);
//        System.out.println("yPixelOffset: " + yPixelOffset);
    }

    private Position xyTors(double x, double y) {
        double phi = Math.PI/6;
        double r = x/Math.cos(phi);
        return new Position(r, y, 0);
    }

    public void hold(/*Weapon to be added*/) {
        //Hold this
    }
}
