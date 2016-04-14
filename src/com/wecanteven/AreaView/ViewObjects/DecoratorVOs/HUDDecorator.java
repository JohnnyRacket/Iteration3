package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.MenuComponentDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
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

    private ViewObjectFactory factory;
    private AreaView areaView;

    private int currentHealth = 0;

    private Position healthBarOffset = new Position(0,0,6);

    public HUDDecorator(ViewObject child, Stats subject, MenuComponentDrawingStrategy drawingStrategy, ViewObjectFactory factory, AreaView areaView) {
        super(child);
        this.subject = subject;
        healthBar = new AnimatedChangeProgressBar(60, 15);
        this.drawingStrategy = drawingStrategy;
        this.factory = factory;
        this.areaView = areaView;
        subject.attach(this);
        update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        drawingStrategy.draw(g, healthBar, getPosition().add(healthBarOffset));
    }

    private boolean tookDamage() {
        return (healthBar.getCurrentProgress() > subject.getHealth()) ;
    }
    private int getDamage() {
        return healthBar.getCurrentProgress() - subject.getHealth();
    }

    @Override
    public void update() {
        if (tookDamage()) {
            areaView.addViewObject(factory.createFloatingTextViewObject(
                    getPosition().add(new Position(0,0,1)),
                    Integer.toString(getDamage()),
                    700,
                    Color.WHITE,
                    4
            ));
            System.out.println("I took damage");
        }
        healthBar.setCurrentProgress(subject.getHealth());
        healthBar.setMaxProgress(subject.getMaxHealth());
    }
}
