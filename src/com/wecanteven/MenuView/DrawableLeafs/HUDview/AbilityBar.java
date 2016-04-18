package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.RoundedHealthBar;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by John on 4/7/2016.
 */
public class AbilityBar extends Drawable implements Observer{
    private Stats stats;
    private RoundedHealthBar manaBar = new RoundedHealthBar(400,20);
    private RoundedHealthBar expBar = new RoundedHealthBar(500, 10);

    public AbilityBar(Stats stats) {
        this.stats = stats;

        manaBar.setCurrentColor(new Color(133, 85, 242));
        manaBar.setDepletedColor(new Color(68, 50, 132));
        //manaBar.setPercent(90);

        expBar.setCurrentColor(new Color(237, 242, 77));
        expBar.setDepletedColor(new Color(116, 119, 37));
        //expBar.setPercent(35);
        update();
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

        manaBar.draw(g2d,x+10,y+70,windowWidth,windowHeight);
        expBar.draw(g2d,x-30,y+95,windowWidth,windowHeight);

        g2d.setColor(Config.TRANSMEDIUMGREY);
        g2d.fillOval(x,y,60,60);
        g2d.fillOval(x+120,y,60,60);
        g2d.fillOval(x+240,y,60,60);
        g2d.fillOval(x+360,y,60,60);
    }

    public void update() {

        manaBar.setPercent((int)((stats.getMana()/stats.getMaxMana())*100f));
        expBar.setPercent((int)stats.getExperience()/100);


    }
}
