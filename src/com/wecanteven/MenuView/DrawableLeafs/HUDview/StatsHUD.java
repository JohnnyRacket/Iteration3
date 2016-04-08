package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.AnimatedChangeProgressBar;
import com.wecanteven.Models.Stats.*;
import com.wecanteven.Observers.Observer;

import java.awt.*;

/**
 * Created by John on 4/7/2016.
 */
public class StatsHUD extends Drawable implements Observer {

    private int health, mana, exp, maxHealth, maxMana, maxExp;
    private Stats stats;

    private AnimatedChangeProgressBar healthBar, manaBar, expBar;

    public StatsHUD(Stats stats){
        this.stats = stats;
        healthBar = new AnimatedChangeProgressBar(200,40);
        manaBar = new AnimatedChangeProgressBar(200,40);
        expBar = new AnimatedChangeProgressBar(200,40);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int xOffset = x + 20;
        int yOffset = y + 20;

        healthBar.draw(g2d,xOffset,yOffset,windowWidth,windowHeight);
        yOffset += (healthBar.getHeight() + 6);
        manaBar.draw(g2d,xOffset,yOffset,windowWidth,windowHeight);
        yOffset += (manaBar.getHeight() + 6);
        expBar.draw(g2d,xOffset,yOffset,windowWidth,windowHeight);
    }

    @Override
    public void update() {
        health = stats.getHealth();
        maxHealth = stats.getMaxHealth();
        healthBar.setCurrentProgress(health);
        healthBar.setMaxProgress(maxHealth);

        mana = stats.getMana();
        maxMana = stats.getMaxMana();
        manaBar.setCurrentProgress(mana);
        manaBar.setMaxProgress(maxMana);

        exp = stats.getExperience();
        maxExp = 100; //TODO fix this
        expBar.setCurrentProgress(exp);
        expBar.setMaxProgress(exp);
    }
}
