package com.wecanteven.MenuView.DrawableLeafs.HUDview;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids.GridAbilityItem;
import com.wecanteven.MenuView.DrawableLeafs.ProgressBars.RoundedHealthBar;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Tuple;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 4/7/2016.
 */
public class AbilityBar extends Drawable implements Observer{
    private Stats stats;
    private AbilityStorage abilityStorage;

    private RoundedHealthBar manaBar = new RoundedHealthBar(400,20);
    private RoundedHealthBar expBar = new RoundedHealthBar(500, 10);
    private GridAbilityItem items[] = new GridAbilityItem[5];

    public AbilityBar(Stats stats,AbilityStorage abilityStorage) {
        this.stats = stats;
        this.stats.attach(this);
        this.abilityStorage = abilityStorage;
        this.abilityStorage.attach(this);

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
        if(items[1]!= null) items[1].draw(g2d,x-15,y-5,90,80);
        g2d.setColor(Config.TRANSMEDIUMGREY);
        g2d.fillOval(x+120,y,60,60);
        if(items[2]!= null) items[2].draw(g2d,x+125,y+5,60,60);
        g2d.setColor(Config.TRANSMEDIUMGREY);
        g2d.fillOval(x+240,y,60,60);
        if(items[3]!= null) items[3].draw(g2d,x+245,y+5,windowWidth,windowHeight);
        g2d.setColor(Config.TRANSMEDIUMGREY);
        g2d.fillOval(x+360,y,60,60);
        if(items[4]!= null) items[4].draw(g2d,x+365,y+5,windowWidth,windowHeight);
    }

    public void update() {

        manaBar.setPercent((int)((stats.getMana()/stats.getMaxMana())*100f));
        expBar.setPercent((int)stats.getExperience()/100);

        Iterator<Tuple<Ability,Integer>> iter = abilityStorage.getAbilityEquipment().getOrderedIterator();
        for(int i =0; i < 5; ++i){
            items[i] = null;
        }
        while(iter.hasNext()){
            Tuple<Ability,Integer> ability = iter.next();
            System.out.println("ADDING ABILITY");
            items[ability.y] = new GridAbilityItem(ability.x.getName(), ability.x.getName(),null);
            items[ability.y].setHeight(50);
            items[ability.y].setWidth(50);
        }

    }
}
