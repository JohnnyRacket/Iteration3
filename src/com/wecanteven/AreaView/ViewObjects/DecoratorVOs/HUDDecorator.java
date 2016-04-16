package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.MenuComponentDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.RoundedHealthBar;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;

import java.awt.*;

/**
 * Created by Alex on 4/11/2016.
 */
public class HUDDecorator extends DecoratorViewObject implements Observer{
    private Stats subject;

    private RoundedHealthBar healthBar;
    private RoundedHealthBar manaBar;
    private MenuComponentDrawingStrategy drawingStrategy;

    private ViewObjectFactory factory;
    private AreaView areaView;

    private int currentHealth = 0;

    private Position healthBarOffset = new Position(0,0,4);

    public HUDDecorator(ViewObject child, Stats subject, MenuComponentDrawingStrategy drawingStrategy, ViewObjectFactory factory, AreaView areaView) {
        super(child);
        this.subject = subject;
        healthBar = new RoundedHealthBar(60, 6);
//        manaBar = new RoundedHealthBar(60, 6);
//        manaBar.setCurrentColor(new Color(43, 130, 251));
//        manaBar.setDepletedColor(new Color(31, 90, 177));
//        manaBar.setyOffset(8);
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
        //drawingStrategy.draw(g, manaBar, getPosition().add(healthBarOffset));
    }

    private boolean tookDamage() {
        return (((float)healthBar.getPercent()/100f * (float)subject.getMaxHealth()) > subject.getHealth()) ;
    }
    private int getDamage() {
        return (int)Math.ceil(((float)healthBar.getPercent()/100f * (float)subject.getMaxHealth()) - (float)subject.getHealth());
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

        }
        healthBar.setPercent((int)(((float)subject.getHealth()/(float)subject.getMaxHealth())*100f));
        //manaBar.setPercent((int)(((float)subject.getMana()/(float)subject.getMaxMana())*100f));
        //healthBar.setCurrentProgress(subject.getHealth());
        //healthBar.setMaxProgress(subject.getMaxHealth());
    }
}
