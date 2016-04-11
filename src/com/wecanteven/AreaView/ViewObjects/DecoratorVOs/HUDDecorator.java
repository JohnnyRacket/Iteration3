package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;

import java.awt.*;

/**
 * Created by Alex on 4/11/2016.
 */
public class HUDDecorator extends DecoratorViewObject implements Observer{
    private Stats subject;

    private int maxHealth;
    private int currentHealth;

    public HUDDecorator(ViewObject child, Stats subject) {
        super(child);
        this.subject = subject;
        update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

    }

    @Override
    public void update() {
        this.maxHealth = subject.getMaxHealth();
        this.currentHealth = subject.getHealth();
    }
}
