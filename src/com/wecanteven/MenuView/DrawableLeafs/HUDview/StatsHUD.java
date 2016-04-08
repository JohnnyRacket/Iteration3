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

    private Color bgColor = Color.GRAY;
    private CircularHealthBar healthBar, manaBar;
    private  AnimatedChangeProgressBar expBar;

    public StatsHUD(Stats stats){
        this.stats = stats;
        healthBar = new CircularHealthBar(180,180);
        healthBar.setCurrentColor(new Color(40,250,80));
        healthBar.setDepletedColor(new Color(20,140,40));
        healthBar.setBorderSize(20);
        healthBar.setBorderColor(new Color(80,80,80));
        //healthBar.setBorderWidth(8);
        manaBar = new CircularHealthBar(180,180);
        manaBar.setCurrentColor(new Color(10,120,255));
        manaBar.setDepletedColor(new Color(10,70,175));
        manaBar.setBorderSize(20);
        manaBar.setBorderColor(new Color(80,80,80));

        expBar = new AnimatedChangeProgressBar(200,50);
        expBar.setCurrentColor(new Color(230,170,50));
        expBar.setDepletedColor(new Color(150,100,25));
        expBar.setBorderWidth(20);
        expBar.setBorderColor(new Color(80,80,80));

        update();
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        int xOffset = x + 20;
        int yOffset = y + 20;


        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        expBar.setWidth(windowWidth - 480);
        expBar.draw(g2d,240,0,windowWidth,windowHeight);


        g2d.setColor(new Color(80,80,80));
        g2d.fillPolygon(new int[]{0,0,300},new int[]{0,300,0},3);

        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 300},new int[]{0,300,0},3);

        g2d.setColor(new Color(95,95,95));
        g2d.fillPolygon(new int[]{0,0,280},new int[]{0,280,0},3);
        g2d.fillPolygon(new int[]{windowWidth,windowWidth,windowWidth - 280},new int[]{0,280,0},3);


        healthBar.draw(g2d,20,20,windowWidth,windowHeight);

        manaBar.draw(g2d,windowWidth - manaBar.getWidth() - 20,20,windowWidth,windowHeight);

    }

    @Override
    public void update() {
        health = stats.getHealth();
        maxHealth = stats.getMaxHealth();
        //healthBar.setCurrentProgress(health);
        //healthBar.setMaxProgress(maxHealth);

        mana = stats.getMana();
        maxMana = stats.getMaxMana();
        //manaBar.setCurrentProgress(mana);
        //manaBar.setMaxProgress(maxMana);

        exp = stats.getExperience();
        maxExp = 100; //TODO fix this
        expBar.setCurrentProgress(70);
        expBar.setMaxProgress(100);
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
