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

public class HandViewObject extends LeafViewObject {
    private double radius;
    private double angle;
    private double height;
    private double offset;
    private int xPixelOffset;
    private int yPixelOffset;
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
        drawingStrategy.draw(graphic, handsImage, getPosition());
        calculatePixelOffsets();
    }

    public void calculatePixelOffsets() {
        xPixelOffset = (int)(radius*Math.cos(angle) + offset*Math.cos(angle + Math.PI/2));
        yPixelOffset = -(int)((radius*Math.sin(angle) + offset*Math.sin(angle + Math.PI/2)+ height)*(CAMERA_TILT_FACTOR) );
//        System.out.println("xPixelOffset: " + xPixelOffset);
//        System.out.println("yPixelOffset: " + yPixelOffset);
    }

    public void hold(/*Weapon to be added*/) {
        //Hold this
    }
}
