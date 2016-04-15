package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.StringDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewTime;

import java.awt.*;

/**
 * Created by alexs on 4/14/2016.
 */
public class FloatingTextViewObject extends LeafViewObject{
    private String text;
    private double distance;
    private Position start;
    private long startTime;
    private long endTime;
    private Color color;

    private static Font font = new Font("Futura", Font.BOLD, 24);


    private float transparency;

    private StringDrawingStrategy drawingStrategy;

    public FloatingTextViewObject(Position position, String text, double distance, long duration, Color color, StringDrawingStrategy drawingStrategy) {
        super(position);
        this.text = text;
        this.distance = distance;
        this.color = color;
        this.drawingStrategy = drawingStrategy;

        this.startTime = ViewTime.getInstance().getCurrentTime();
        this.endTime = this.startTime + duration;

        this.start = position;
        tick();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        g.setFont(font);
        drawingStrategy.draw(g, text, getPosition());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        //We dont add to fog of war
    }

    private void tick() {
        float percentage = calculatePercentage();
        adjustTransparency(percentage);
        adjustPosition(percentage);
        System.out.println("%: " + percentage);
        System.out.println("Transp: " + transparency);
        System.out.println("Position: " + getPosition());

        if (ViewTime.getInstance().getCurrentTime() < endTime) {
            ViewTime.getInstance().register( this::tick, 20);
        } else {
            clean();
        }
    }

    private void clean() {

    }

    private float calculatePercentage() {
        System.out.println(ViewTime.getInstance().getCurrentTime());
        System.out.println(startTime);
        System.out.println((ViewTime.getInstance().getCurrentTime() - startTime));
        System.out.println((double)(endTime - startTime));
        return (float)((double)(ViewTime.getInstance().getCurrentTime() - startTime)/(endTime - startTime));
    }

    private void adjustTransparency(float percentage) {
       this.transparency = percentage < 1 ? 1f - percentage : 0;
    }

    private void adjustPosition(float percentage) {
        setPosition(start.add(new Position(0,0,percentage*distance)));
    }



}
