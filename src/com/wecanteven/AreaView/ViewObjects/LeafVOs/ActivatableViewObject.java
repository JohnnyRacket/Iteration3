package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.SimpleDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.Observers.Activatable;
import com.wecanteven.Observers.Observer;

import java.awt.*;


/**
 * Created by alexs on 4/2/2016.
 */
public class ActivatableViewObject extends LeafViewObject implements Observer {
    private SimpleDynamicImage activeImage;
    private SimpleDynamicImage inactiveImage;
    private Activatable subject;
    private SimpleDynamicImage currentImage;
    private DynamicImageDrawingStrategy painter;

    public ActivatableViewObject(Position position, Activatable subject, SimpleDynamicImage activeImage, SimpleDynamicImage inactiveImage, DynamicImageDrawingStrategy painter) {
        super(position);
        this.activeImage = activeImage;
        this.inactiveImage = inactiveImage;
        this.subject = subject;
        this.painter = painter;
        update();
    }

    @Override
    public void update() {
        currentImage = subject.isActive() ? activeImage : inactiveImage;
    }

    @Override
    public void draw(Graphics2D g) {
        painter.draw(g, currentImage, getPosition());
    }
}
