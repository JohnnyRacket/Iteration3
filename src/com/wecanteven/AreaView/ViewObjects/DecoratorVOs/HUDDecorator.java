package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.MenuComponentDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.AnimatedChangeProgressBar;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;

import java.awt.*;

/**
 * Created by Alex on 4/11/2016.
 */
public class HUDDecorator extends DecoratorViewObject implements Observer{
    private Stats subject;

    private AnimatedChangeProgressBar healthBar;
    private MenuComponentDrawingStrategy drawingStrategy;

    private Position healthBarOffset = new Position(0,0,6);

    public HUDDecorator(ViewObject child, Stats subject, MenuComponentDrawingStrategy drawingStrategy) {
        super(child);
        this.subject = subject;
        healthBar = new AnimatedChangeProgressBar(60, 15);
        this.drawingStrategy = drawingStrategy;
        update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        drawingStrategy.draw(g, healthBar, getPosition().add(healthBarOffset));
    }

    @Override
    public void update() {
        healthBar.setCurrentProgress(subject.getHealth());
        healthBar.setMaxProgress(subject.getMaxHealth());
    }
}
